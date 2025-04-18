package com.example.atm_report_service.services;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.atm_report_service.dto.TransactionMapper;
import com.example.atm_report_service.dto.TransactionRequestDTO;
import com.example.atm_report_service.dto.TransactionResponseDTO;
import com.example.atm_report_service.model.TransactionEntity;
import com.example.atm_report_service.model.TransactionRepository;
import com.example.atm_report_service.model.enums.AtmType;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    @Transactional
    public void saveTransactions(List<TransactionRequestDTO> transactions, AtmType atmType) {

        for (TransactionRequestDTO dto: transactions) {

            TransactionEntity transaction = TransactionMapper.toEntity(dto, atmType);
            transactionRepository.save(transaction);
        }
    }

    public List<TransactionResponseDTO> getAllTransactions() {
    return transactionRepository.findAll().stream()
            .map(TransactionMapper::toResponseDto)
            .toList();
}

    public void deleteAllTransactions() {
        transactionRepository.deleteAll();
    }
}
