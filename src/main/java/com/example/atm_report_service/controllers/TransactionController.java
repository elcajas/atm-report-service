package com.example.atm_report_service.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.atm_report_service.dto.TransactionRequestDTO;
import com.example.atm_report_service.services.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> saveTransactions(@RequestBody List<TransactionRequestDTO> transactions) {
        transactionService.saveTransactions(transactions);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteAllTransactions() {
        transactionService.deleteAllTransactions();
        return ResponseEntity.noContent().build();
    }

}
