package com.example.wehago.transaction.service;

import com.example.wehago.client.service.ClientService;
import com.example.wehago.transaction.dto.*;
import com.example.wehago.transaction.mapper.TransactionMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionMapper transactionMapper;

    private ClientService clientService;

    public TransactionServiceImpl(TransactionMapper transactionMapper,ClientService clientService) {
        this.clientService = clientService;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public List<TransactionResponseDto> getAllTransactions(TransactionCondition condition) {
        return transactionMapper.selectAllTransactions(condition);
    }

    @Override
    public void insertTransaction(TransactionRequestDto requestDto) {
        int expectedRecoverDay = clientService.selectExpectedRecoveryDaysByClientId(requestDto.getClientId());
        LocalDate transactionDate = LocalDate.parse(requestDto.getTransactionDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        LocalDate expectedPaymentDate = transactionDate.plusDays(expectedRecoverDay);
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .clientId(requestDto.getClientId())
                .transactionDate(requestDto.getTransactionDate())
                .transactionAmount(requestDto.getTransactionAmount())
                .expectedPaymentDate(String.valueOf(expectedPaymentDate))
                .recoveredDate(requestDto.getRecoveredDate())
                .recoveredAmount(requestDto.getRecoveredAmount())
                .build();
        transactionMapper.insertTransaction(transactionEntity);
    }

    @Override
    public List<TransactionRecoveryStatsResponseDto> getTransactionRecoveryStats() {
        return transactionMapper.selectTransactionRecoveryStats();
    }

    @Override
    public void updateTransaction(TransactionRequestDto requestDto) {
        TransactionEntity transactionEntity = TransactionEntity.builder()
                .transactionId(requestDto.getTransactionId())
                .clientId(requestDto.getClientId())
                .transactionDate(requestDto.getTransactionDate())
                .recoveredDate(requestDto.getRecoveredDate())
                .transactionAmount(requestDto.getTransactionAmount())
                .recoveredAmount(requestDto.getRecoveredAmount())
                .build();
        transactionMapper.updateTransaction(transactionEntity);
    }

    @Override
    public String selectTransactionDateByTransactionId(String transactionId) {
        return transactionMapper.selectTransactionDateByTransactionId(transactionId);
    }


}
