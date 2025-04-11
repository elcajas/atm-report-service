package com.example.atm_report_service.services;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.atm_report_service.dto.TransactionMapper;
import com.example.atm_report_service.dto.TransactionRequestDTO;
import com.example.atm_report_service.model.TransactionEntity;
import com.example.atm_report_service.model.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Transactional
    public void saveTransactions(List<TransactionRequestDTO> transactions) {

        for (TransactionRequestDTO dto: transactions) {

            TransactionEntity transaction = TransactionMapper.toEntity(dto);
            transactionRepository.save(transaction);
        }
    }

    public void deleteAllTransactions() {
        transactionRepository.deleteAll();
    }
}
