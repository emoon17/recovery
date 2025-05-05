package com.example.wehago.predict.scheduler;

import com.example.wehago.predict.service.PredictionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class PredictionScheduler {

    private final PredictionService predictionService;


    public PredictionScheduler(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    // 매일 자정 실행 (0시 5분, DB 정리 시간 고려)
    @Scheduled(cron = "0 5 0 * * *")
//    @Scheduled(cron = "0 * * * * *") // 테스트 1분마다
    public void runDailyPrediction() {
        LocalDate statDate = LocalDate.now().minusDays(1);
        log.info("[예측 스케줄러] {} 거래 회수 지연 예측 시작", statDate);
        try {
            predictionService.predictAndSaveDelays();
            log.info("[예측 스케줄러] {} 기준 예측 완료", statDate);
        } catch (Exception e) {
            log.error("[예측 스케줄러] 실패: {}", e.getMessage(), e);

        }
    }

//        @Scheduled(cron = "0 * * * * *") // 매 분마다
    @Scheduled(cron = "0 3 0 * * *")
    public void runTrainingJob() {
        LocalDate statDate = LocalDate.now().minusDays(1);
        log.info("[모델 학습 스케줄러] {} 모델 학습 스케줄러 시작", statDate);
        try {
            predictionService.predictTrain();
            log.info("[모델 학습 스케줄러] {} 모델 학습 스케줄러 완료", statDate);
        } catch (Exception e) {
            log.error("[모델 학습 스케줄러] 실패: {}", e.getMessage(), e);

        }
    }
}
