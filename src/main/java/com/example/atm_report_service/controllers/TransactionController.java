package com.example.atm_report_service.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.atm_report_service.dto.TransactionRequestDTO;
import com.example.atm_report_service.dto.TransactionResponseDTO;
import com.example.atm_report_service.model.enums.AtmType;
import com.example.atm_report_service.services.TransactionService;
import com.example.atm_report_service.utils.CsvUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping(consumes = { "application/json", "application/xml", "application/x-yaml", "text/yaml" })
    public ResponseEntity<Void> saveTransactions(
            @RequestBody List<TransactionRequestDTO> transactions,
            @RequestHeader("ATM") String atmTypeHeader ) {

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
