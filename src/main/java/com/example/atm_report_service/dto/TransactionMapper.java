package com.example.atm_report_service.dto;

import com.example.atm_report_service.model.TransactionEntity;
import com.example.atm_report_service.model.enums.AtmType;

public class TransactionMapper {
    public static TransactionEntity toEntity(TransactionRequestDTO dto, AtmType atmType){
        return TransactionEntity.builder()
                .atmType(atmType)
                .transactionId(dto.getTransactionId())
                .transactionDate(dto.getTransactionDate())
                .cardNumber(dto.getCardNumber())
                .transactionType(dto.getTransactionType())
                .amount(dto.getAmount())
                .destinationCardNumber(dto.getDestinationCardNumber())
                .build();
    }

    public static TransactionResponseDTO toResponseDto(TransactionEntity entity) {
        return TransactionResponseDTO.builder()
                .atmType(entity.getAtmType())
                .transactionId(entity.getTransactionId())
                .transactionDate(entity.getTransactionDate())
                .cardNumber(entity.getCardNumber())
                .transactionType(entity.getTransactionType())
                .amount(entity.getAmount())
                .destinationCardNumber(entity.getDestinationCardNumber())
                .build();
    }
}
