package com.example.atm_report_service.model;

import com.example.atm_report_service.model.enums.AtmType;
import com.example.atm_report_service.model.enums.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private AtmType atmType;
    private String transactionId;
    private LocalDateTime transactionDate;
    private String cardNumber;
    private TransactionType transactionType;
    private BigDecimal amount;
    private String destinationCardNumber;
}
