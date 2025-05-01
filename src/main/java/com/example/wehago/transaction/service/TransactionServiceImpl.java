package com.example.wehago.transaction.service;

import com.example.wehago.transaction.dto.TransactionCondition;
import com.example.wehago.transaction.dto.TransactionEntity;
import com.example.wehago.transaction.dto.TransactionRequestDto;
import com.example.wehago.transaction.dto.TransactionResponseDto;
import com.example.wehago.transaction.mapper.TransactionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @Override
    public List<TransactionResponseDto> getAllTransactions(TransactionCondition condition) {
        return transactionMapper.selectAllTransactions(condition);
    }

    @Override
    public void insertTransaction(TransactionRequestDto requestDto) {
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .clientId(requestDto.getClientId())
                .transactionDate(requestDto.getTransactionDate())
                .transactionAmount(requestDto.getTransactionAmount())
                .expectedPaymentDate(requestDto.getExpectedPaymentDate())
                .recoveredDate(requestDto.getRecoveredDate())
                .recoveredAmount(requestDto.getRecoveredAmount())
                .build();
        transactionMapper.insertTransaction(transactionEntity);
    }
}
