package com.example.wehago.recovery.scheduler;

import com.example.wehago.recovery.dto.RecoveryStatEntity;
import com.example.wehago.recovery.service.RecoverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class RecoveryStatScheduler {

    private RecoverService recoverService;

    public RecoveryStatScheduler( RecoverService recoverService) {
        this.recoverService = recoverService;
    }

        @Scheduled(cron = "0 0 0 * * *") // 매일 자정
//    @Scheduled(cron = "0 * * * * *") // 테스트 1분마다
    public void runDailyRecoveryStat() {
        LocalDate statDate = LocalDate.now().minusDays(1);
        log.info("[스케줄러 실행] {} 기준 통계 시작", statDate);
        try {
            recoverService.getRecoveryStats(new RecoveryStatEntity());
            log.info("[회수율 통계] {} 기준 통계 완료", statDate);
        } catch (Exception e) {
            log.error("[회수율 통계] 실패: {}", e.getMessage(), e);
        }
    }
}
