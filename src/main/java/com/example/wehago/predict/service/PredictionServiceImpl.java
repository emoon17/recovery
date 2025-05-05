package com.example.wehago.predict.service;

import com.example.wehago.predict.dto.PredictRiskTableResponse;
import com.example.wehago.predict.dto.PredictionResponseDto;
import com.example.wehago.predict.dto.PredictionRiskEntity;
import com.example.wehago.predict.dto.PredictionTargetDto;
import com.example.wehago.predict.flaskclient.FlaskPredictionClient;
import com.example.wehago.predict.mapper.PredictionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class PredictionServiceImpl implements PredictionService {

    private final PredictionMapper predictionMapper;
    private final FlaskPredictionClient flaskClient;

    public PredictionServiceImpl(PredictionMapper predictionMapper, FlaskPredictionClient flaskClient) {
        this.predictionMapper = predictionMapper;
        this.flaskClient = flaskClient;
    }

    @Override
    public void predictAndSaveDelays() {
        List<PredictionTargetDto> targets = predictionMapper.selectPredictionTargets();

        for (PredictionTargetDto dto : targets) {
            try {
                // 예측 모델 응답값
                PredictionResponseDto response = flaskClient.predict(dto);

                PredictionRiskEntity risk = PredictionRiskEntity.builder()
                        .clientId(dto.getClientId())
                        .txId(dto.getTxId())
                        .predictedDelay(response.getPredictedDelay())
                        .riskLevel(response.getRiskLevel())
                        .comment(response.getComment())
                        .build();
                predictionMapper.insertRiskPrediction(risk);

            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }



    @Override
    public void predictTrain() {
        try {
            flaskClient.train();
            log.info(" 모델 학습 요청 성공 (/ml/train)");
        } catch (Exception e) {
            log.error(" 모델 학습 요청 실패: " + e.getMessage(), e);
        }
    }

    @Override
    public List<PredictRiskTableResponse> getAllPredicts() {
        LocalDate yesterDate = LocalDate.now().minusDays(1);
        String yesterday = yesterDate.toString();
        return predictionMapper.getAllPredicts(yesterday);
    }
}
