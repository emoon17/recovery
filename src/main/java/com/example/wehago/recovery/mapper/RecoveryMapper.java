package com.example.wehago.recovery.mapper;

import com.example.wehago.recovery.dto.RecoverStatResponseDto;
import com.example.wehago.recovery.dto.RecoveryRequestDto;
import com.example.wehago.recovery.dto.RecoveryStatEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecoveryMapper {

    void bulkInsertRecoveryStats(List<RecoveryStatEntity> entities);
    List<RecoverStatResponseDto> selectRecoveryAllStats(@Param("entity") RecoveryStatEntity recoveryStatEntity);
}
