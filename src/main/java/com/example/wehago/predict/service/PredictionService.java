package com.example.wehago.predict.service;

import com.example.wehago.predict.dto.PredictRiskTableResponse;

import java.util.List;

public interface PredictionService {
    void predictAndSaveDelays();
    void predictTrain();
    List<PredictRiskTableResponse> getAllPredicts();
}
