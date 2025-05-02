package com.example.wehago.recovery.controller;

import com.example.wehago.common.ApiResponse;
import com.example.wehago.recovery.dto.RecoveryStatEntity;
import com.example.wehago.recovery.service.RecoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.wehago.common.ApiResponse.success;

@RestController
@RequestMapping("/api/recovery-stat")
public class RecoveryStatController {

    private final RecoverService recoverService;

    public RecoveryStatController(RecoverService recoverService) {
        this.recoverService = recoverService;
    }

    @PostMapping("/calculate")
    public ApiResponse<String> calculate() {
        recoverService.getRecoveryStats(new RecoveryStatEntity());
        return success("ok");

    }
}