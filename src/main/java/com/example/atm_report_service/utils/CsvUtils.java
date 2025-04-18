package com.example.atm_report_service.utils;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.atm_report_service.dto.TransactionRequestDTO;
import com.example.atm_report_service.model.enums.TransactionType;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CsvUtils {
    public static List<TransactionRequestDTO> parseCsv(String csvBody) {
        List<TransactionRequestDTO> transactions = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new StringReader(csvBody))) {
            List<String[]> rows = reader.readAll();

            for (int i = 1; i < rows.size(); i++) {
                String[] line = rows.get(i);

                TransactionRequestDTO dto = TransactionRequestDTO.builder()
                        .transactionId(line[0])
                        .transactionDate(LocalDateTime.parse(line[1]))
                        .cardNumber(line[2])
                        .transactionType(TransactionType.valueOf(line[3]))
                        .amount(new BigDecimal(line[4]))
                        .destinationCardNumber(line[5])
                        .build();

                transactions.add(dto);
            }

        } catch (IOException | CsvException | RuntimeException e) {
            throw new IllegalArgumentException("Invalid CSV format", e);
        }

        return transactions;
    }
}
