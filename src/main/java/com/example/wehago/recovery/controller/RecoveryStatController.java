package com.example.wehago.recovery.controller;

import com.example.wehago.common.ApiResponse;
import com.example.wehago.recovery.dto.RecoverStatResponseDto;
import com.example.wehago.recovery.dto.RecoveryRequestDto;
import com.example.wehago.recovery.dto.RecoveryStatEntity;
import com.example.wehago.recovery.service.RecoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.wehago.common.ApiResponse.fail;
import static com.example.wehago.common.ApiResponse.success;

@RestController
@RequestMapping("/api/recovery")
public class RecoveryStatController {

    private final RecoverService recoverService;

    public RecoveryStatController(RecoverService recoverService) {
        this.recoverService = recoverService;
    }

    /**
     * 테스트용
     * */
    @PostMapping("/calculate")
    public ApiResponse<String> calculate() {
        recoverService.getRecoveryStats(new RecoveryStatEntity());
        return success("ok");

    }

    /**
     * 전일 날짜 거래처 회수율 전체 조회
     * */
    @GetMapping("/getRecoveryAllStats")
    public ApiResponse<List<RecoverStatResponseDto>> getRecoveryAllStats() {
        try {
            List<RecoverStatResponseDto> resList = recoverService.getRecoveryAllStats();
            return success(resList);
        } catch (Exception e) {
            return fail("거래처 회수율 전체 조회 실패 : " + e.getMessage());
        }
    }

}