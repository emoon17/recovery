package com.example.wehago.predict.service;

import com.example.wehago.predict.dto.PredictRiskTableResponse;
import com.example.wehago.predict.dto.PredictionRequestDto;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface PredictionService {
    void predictAndSaveDelays();
    void predictTrain();
    List<PredictRiskTableResponse> getAllPredicts();
    void sendTestMail (List<PredictionRequestDto> requestDtos);
}
