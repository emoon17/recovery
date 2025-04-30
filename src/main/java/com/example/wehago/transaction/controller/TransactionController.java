package com.example.wehago.transaction.controller;

import com.example.wehago.common.ApiResponse;
import com.example.wehago.transaction.dto.TransactionCondition;
import com.example.wehago.transaction.dto.TransactionResponseDto;
import com.example.wehago.transaction.service.TransactionService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/api/transaction")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/getAllTransactions")
    public ApiResponse<List<TransactionResponseDto>> getAllTransactions(TransactionCondition condition) {
        return ApiResponse.success(transactionService.getAllTransactions(condition));
    }
}
