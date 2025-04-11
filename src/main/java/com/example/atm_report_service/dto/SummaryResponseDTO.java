package com.example.atm_report_service.dto;

import com.example.atm_report_service.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SummaryResponseDTO {
    private LocalDate date;
    private String atm;
    private TransactionType transactionType;
    private BigDecimal totalAmount;
    private Long count;
}
