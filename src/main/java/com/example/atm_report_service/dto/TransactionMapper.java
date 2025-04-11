package com.example.atm_report_service.dto;

import com.example.atm_report_service.model.TransactionEntity;

public class TransactionMapper {
    public static TransactionEntity toEntity(TransactionRequestDTO dto){
        return TransactionEntity.builder()
                .atmType(dto.getAtmType())
                .transactionId(dto.getTransactionId())
                .transactionDate(dto.getTransactionDate())
                .cardNumber(dto.getCardNumber())
                .transactionType(dto.getTransactionType())
                .amount(dto.getAmount())
                .destinationCardNumber(dto.getDestinationCardNumber())
                .build();
    }
}
