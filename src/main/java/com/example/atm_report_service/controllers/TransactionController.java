package com.example.atm_report_service.controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.atm_report_service.dto.TransactionRequestDTO;
import com.example.atm_report_service.dto.TransactionResponseDTO;
import com.example.atm_report_service.model.enums.AtmType;
import com.example.atm_report_service.services.TransactionService;
import com.example.atm_report_service.utils.CsvUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.http.MediaType;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> uploadTransactionFile(
            @RequestParam("file") MultipartFile file,
            @RequestHeader("ATM") String atmTypeHeader) {

        AtmType atmType = AtmType.valueOf(atmTypeHeader.toUpperCase());

        try {
            String contentType = file.getContentType();
            String fileContent = new String(file.getBytes(), StandardCharsets.UTF_8);
            List<TransactionRequestDTO> transactions;

            if (contentType == null) {
                throw new IllegalArgumentException("Unknown content type");
            }

            switch (contentType) {
                case "application/json":
                    ObjectMapper objectMapper = new ObjectMapper();
                    objectMapper.registerModule(new JavaTimeModule());
                    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

                    transactions = objectMapper.readValue(fileContent,
                            new TypeReference<List<TransactionRequestDTO>>() {
                            });
                    break;

                case "application/xml":
                case "text/xml":
                    XmlMapper xmlMapper = new XmlMapper();
                    xmlMapper.registerModule(new JavaTimeModule());
                    xmlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

                    transactions = xmlMapper.readValue(fileContent,
                            new TypeReference<List<TransactionRequestDTO>>() {
                            });
                    break;

                case "text/csv":
                    transactions = CsvUtils.parseCsv(fileContent);
                    break;

                case "application/x-yaml":
                case "text/yaml":
                    ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
                    yamlMapper.registerModule(new JavaTimeModule());
                    yamlMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

                    transactions = yamlMapper.readValue(fileContent, new TypeReference<List<TransactionRequestDTO>>() {
                    });
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported file type: " + contentType);
            }

            transactionService.saveTransactions(transactions, atmType);
            return ResponseEntity.ok().build();

        } catch (IOException e) {
            e.printStackTrace(); // Log to console for now
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping(consumes = { "application/json", "application/xml", "application/x-yaml", "text/yaml" })
    public ResponseEntity<Void> saveTransactions(
            @RequestBody List<TransactionRequestDTO> transactions,
            @RequestHeader("ATM") String atmTypeHeader) {

        AtmType atmType;
        atmType = AtmType.valueOf(atmTypeHeader.toUpperCase());

        transactionService.saveTransactions(transactions, atmType);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/csv", consumes = "text/csv")
    public ResponseEntity<Void> saveCsvTransactions(
            @RequestBody String csvBody,
            @RequestHeader("ATM") String atmTypeHeader) {

        AtmType atmType = AtmType.valueOf(atmTypeHeader.toUpperCase());
        List<TransactionRequestDTO> transactions = CsvUtils.parseCsv(csvBody);
        transactionService.saveTransactions(transactions, atmType);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
        List<TransactionResponseDTO> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAllTransactions() {
        transactionService.deleteAllTransactions();
        return ResponseEntity.noContent().build();
    }

}
