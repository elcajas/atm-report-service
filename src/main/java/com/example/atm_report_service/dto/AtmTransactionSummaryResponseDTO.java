package com.example.atm_report_service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.atm_report_service.model.enums.AtmType;
import com.example.atm_report_service.model.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtmTransactionSummaryResponseDTO {
    private LocalDate date;
    private AtmType atmType;
    private TransactionType trasactionType;
    private BigDecimal totalAmount;
    private long count;
}
