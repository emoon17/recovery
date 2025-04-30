package com.example.wehago.transaction.service;

import com.example.wehago.transaction.dto.TransactionCondition;
import com.example.wehago.transaction.dto.TransactionResponseDto;

import java.util.List;

public interface TransactionService {

    List<TransactionResponseDto> getAllTransactions(TransactionCondition condition);
}
