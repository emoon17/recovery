package com.example.wehago.transaction.mapper;

import com.example.wehago.transaction.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TransactionMapper {

    List<TransactionResponseDto> selectAllTransactions(@Param("condition") TransactionCondition condition);
    void insertTransaction(@Param("entity") TransactionEntity entity);
    List<TransactionRecoveryStatsResponseDto> selectTransactionRecoveryStats();
}
