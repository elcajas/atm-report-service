package com.example.atm_report_service.dto;

import com.example.atm_report_service.model.enums.AtmType;
import com.example.atm_report_service.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequestDTO {
    private AtmType atmType;
    private String transactionId;
    private LocalDateTime transactionDate;
    private String cardNumber;
    private TransactionType transactionType;
    private BigDecimal amount;
    private String destinationCardNumber;
}
