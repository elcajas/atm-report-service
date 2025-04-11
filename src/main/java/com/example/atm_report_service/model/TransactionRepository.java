package com.example.atm_report_service.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.atm_report_service.model.enums.AtmType;
import com.example.atm_report_service.model.enums.TransactionType;


@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByTransactionDateBetween(LocalDateTime start, LocalDateTime end);

    List<TransactionEntity> findByAtmTypeAndTransactionDateBetween(AtmType atmType, LocalDateTime start, LocalDateTime end);

    List<TransactionEntity> findByTransactionTypeAndTransactionDateBetween(TransactionType transactionType, LocalDateTime start, LocalDateTime end);

    List<TransactionEntity> findByAtmTypeAndTransactionTypeAndTransactionDateBetween(AtmType atmType, TransactionType transactionType, LocalDateTime start, LocalDateTime end);

    void deleteAll();
}
