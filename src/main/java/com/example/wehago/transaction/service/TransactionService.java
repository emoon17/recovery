package com.example.wehago.transaction.service;

import com.example.wehago.transaction.dto.TransactionCondition;
import com.example.wehago.transaction.dto.TransactionRequestDto;
import com.example.wehago.transaction.dto.TransactionResponseDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TransactionService {

    List<TransactionResponseDto> getAllTransactions(TransactionCondition condition);
    void insertTransaction( TransactionRequestDto requestDto);
}
