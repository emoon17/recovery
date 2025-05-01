package com.example.wehago.transaction.controller;

import com.example.wehago.common.ApiResponse;
import com.example.wehago.transaction.dto.TransactionCondition;
import com.example.wehago.transaction.dto.TransactionRequestDto;
import com.example.wehago.transaction.dto.TransactionResponseDto;
import com.example.wehago.transaction.service.TransactionService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.wehago.common.ApiResponse.fail;
import static com.example.wehago.common.ApiResponse.success;

@Slf4j
@RequestMapping("/api/transaction")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/getAllTransactions")
    public ApiResponse<List<TransactionResponseDto>> getAllTransactions(@RequestBody TransactionCondition condition) {
     return success(transactionService.getAllTransactions(condition));
    }

    @PostMapping("/insertTransaction")
    public ApiResponse<Void> insertTransaction(@RequestBody TransactionRequestDto requestDto) {
        try {
            transactionService.insertTransaction(requestDto);
            return success(null);
        } catch (Exception  e){
            return fail("거래 등록 실패 : " + e.getMessage());
        }
    }
}
