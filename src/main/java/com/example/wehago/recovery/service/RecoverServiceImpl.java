package com.example.wehago.recovery.service;

import com.example.wehago.recovery.dto.RecoverStatResponseDto;
import com.example.wehago.recovery.dto.RecoveryRequestDto;
import com.example.wehago.recovery.dto.RecoveryStatEntity;
import com.example.wehago.recovery.mapper.RecoveryMapper;
import com.example.wehago.transaction.dto.TransactionRecoveryStatsResponseDto;
import com.example.wehago.transaction.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecoverServiceImpl implements RecoverService {

    private TransactionService transactionService;

    private RecoveryMapper recoveryMapper;

    public RecoverServiceImpl(TransactionService transactionService, RecoveryMapper recoveryMapper) {
        this.transactionService = transactionService;
        this.recoveryMapper = recoveryMapper;
    }

    @Override
    public void getRecoveryStats(RecoveryStatEntity entity) {
        List<TransactionRecoveryStatsResponseDto> resDto = transactionService.getTransactionRecoveryStats();


        List<RecoveryStatEntity> entities = resDto.stream()
                .map(dto -> RecoveryStatEntity.builder()
                        .clientId(dto.getClientId())
                        .month(dto.getMonth())
                        .totalAmount(dto.getTotalAmount())
                        .recoveredAmount(dto.getRecoveredAmount())
                        .recoveredRate(dto.getRecoveredRate())
                        .avgRecoveredDays(dto.getAvgRecoveredDays())
                        .build())
                .collect(Collectors.toList());
        recoveryMapper.bulkInsertRecoveryStats(entities);

    }

    @Override
    public List<RecoverStatResponseDto> getRecoveryAllStats(RecoveryRequestDto request) {
        RecoveryStatEntity entity = RecoveryStatEntity.builder()
                .createdAt(request.getCreatedAt())
                .build();
        return recoveryMapper.selectRecoveryAllStats(entity);
    }


}
