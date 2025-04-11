package com.example.atm_report_service.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.atm_report_service.dto.AtmSummaryResponseDTO;
import com.example.atm_report_service.dto.AtmTransactionSummaryResponseDTO;
import com.example.atm_report_service.dto.DailySummaryResponseDTO;
import com.example.atm_report_service.dto.TransactionSummaryResponseDTO;
import com.example.atm_report_service.model.TransactionEntity;
import com.example.atm_report_service.model.TransactionRepository;
import com.example.atm_report_service.model.enums.AtmType;
import com.example.atm_report_service.model.enums.TransactionType;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {
    
    private final TransactionRepository transactionRepository;

    public List<DailySummaryResponseDTO> getDailySummaries(LocalDate startDate, LocalDate  endDate) {

        List<DailySummaryResponseDTO> summaries = new ArrayList<>();

        for(LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {

            LocalDateTime start = date.atStartOfDay();
            LocalDateTime end = date.plusDays(1).atStartOfDay();
            List<TransactionEntity> transactions = transactionRepository.findByTransactionDateBetween(start, end);
    
            BigDecimal totalAmount = transactions.stream()
                .map(TransactionEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    
            long count = transactions.size();

            summaries.add(DailySummaryResponseDTO.builder()
                .date(date)
                .totalAmount(totalAmount)
                .count(count)
                .build());
        }

        return summaries;
    }

    public DailySummaryResponseDTO getDailySummary(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        List<TransactionEntity> transactions = transactionRepository.findByTransactionDateBetween(start, end);

        BigDecimal totalAmount = transactions.stream()
                .map(TransactionEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    
        long count = transactions.size();

        return DailySummaryResponseDTO.builder()
            .date(date)
            .totalAmount(totalAmount)
            .count(count)
            .build();
    }

    public AtmSummaryResponseDTO getAtmTypeSummary(AtmType atmType, LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        List<TransactionEntity> transactions = transactionRepository.findByAtmTypeAndTransactionDateBetween(atmType, start, end);
        
        BigDecimal totalAmount = transactions.stream()
                .map(TransactionEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    
        long count = transactions.size();

        return AtmSummaryResponseDTO.builder()
            .date(date)
            .atmType(atmType)
            .totalAmount(totalAmount)
            .count(count)
            .build();
    }

    public TransactionSummaryResponseDTO getTransactionTypeSummary(TransactionType transactionType, LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        List<TransactionEntity> transactions = transactionRepository.findByTransactionTypeAndTransactionDateBetween(transactionType, start, end);
        
        BigDecimal totalAmount = transactions.stream()
                .map(TransactionEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    
        long count = transactions.size();

        return TransactionSummaryResponseDTO.builder()
            .date(date)
            .transactionType(transactionType)
            .totalAmount(totalAmount)
            .count(count)
            .build();
    }
    
    public AtmTransactionSummaryResponseDTO getAtmTypeTransactionTypeSummary(AtmType atmType, TransactionType transactionType, LocalDate date) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        List<TransactionEntity> transactions = transactionRepository.findByAtmTypeAndTransactionTypeAndTransactionDateBetween(atmType, transactionType, start, end);
    
        BigDecimal totalAmount = transactions.stream()
                .map(TransactionEntity::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    
        long count = transactions.size();

        return AtmTransactionSummaryResponseDTO.builder()
            .date(date)
            .atmType(atmType)
            .trasactionType(transactionType)
            .totalAmount(totalAmount)
            .count(count)
            .build();
    }

}
