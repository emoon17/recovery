package com.example.wehago.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
@RequiredArgsConstructor
public class RecoveryStatScheduler {

    @Scheduled(cron = "0 0 0 * * *") // 매일 자정
    public void runDailyRecoveryStat() {
        LocalDate statDate = LocalDate.now().minusDays(1);
        log.info("[스케줄러 실행] {} 기준 통계 시작", statDate);
    }
}
