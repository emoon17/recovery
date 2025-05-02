package com.example.wehago.recovery.service;

import com.example.wehago.recovery.dto.RecoverStatResponseDto;
import com.example.wehago.recovery.dto.RecoveryStatEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface RecoverService {


    void getRecoveryStats(@Param("entity")RecoveryStatEntity entity);
}
