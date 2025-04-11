package com.example.atm_report_service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailySummaryResponseDTO {
    private LocalDate date;
    private BigDecimal totalAmount;
    private Long count;
}
