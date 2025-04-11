package com.example.atm_report_service.controllers;

import java.time.LocalDate;
// import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.atm_report_service.dto.AtmSummaryResponseDTO;
import com.example.atm_report_service.dto.AtmTransactionSummaryResponseDTO;
import com.example.atm_report_service.dto.DailySummaryResponseDTO;
import com.example.atm_report_service.dto.TransactionSummaryResponseDTO;
import com.example.atm_report_service.model.enums.AtmType;
import com.example.atm_report_service.model.enums.TransactionType;
import com.example.atm_report_service.services.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    
    private final ReportService reportService;

    // @GetMapping("/daily")
    // public List<DailySummaryResponseDTO> getDailySummary(@RequestParam("startDate") LocalDate startDate,@RequestParam("endDate") LocalDate endDate) {
    //     return reportService.getDailySummary(startDate, endDate);
    // }

    @GetMapping("/daily")
    public DailySummaryResponseDTO getDailySummary(@RequestParam("date") LocalDate date) {
        return reportService.getDailySummary(date);
    }

    @GetMapping("/atm/{atmType}")
    public AtmSummaryResponseDTO getAtmTypeSummary(@PathVariable AtmType atmType, @RequestParam("date") LocalDate date) {
        return reportService.getAtmTypeSummary(atmType, date);
    }

    @GetMapping("/type/{transactionType}")
    public TransactionSummaryResponseDTO getTransactionTypeSummary(@PathVariable TransactionType transactionType, @RequestParam("date") LocalDate date) {
        return reportService.getTransactionTypeSummary(transactionType, date);
    }
    
    @GetMapping("/atm/{atmType}/type/{transactionType}")
    public AtmTransactionSummaryResponseDTO getAtmTypeSummary(@PathVariable AtmType atmType, @PathVariable TransactionType transactionType, @RequestParam("date") LocalDate date) {
        return reportService.getAtmTypeTransactionTypeSummary(atmType, transactionType, date);
    }

}
