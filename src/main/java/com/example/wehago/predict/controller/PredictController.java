package com.example.wehago.predict.controller;

import com.example.wehago.common.ApiResponse;
import com.example.wehago.predict.dto.PredictRiskTableResponse;
import com.example.wehago.predict.service.PredictionService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/predict")
public class PredictController {

    private final WebClient webClient;
    private final PredictionService predictionService;
    public PredictController(WebClient webClient, PredictionService predictionService) {
        this.webClient = webClient;
        this.predictionService = predictionService;
    }

    // 테스트용
    @PostMapping("/delayDays")
    public Mono<String> predictDelay (@RequestBody Map<String, String> request) {
        return webClient.post()
                .uri("/predict")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(String.class);
    }

    /**
     * 회수 지연 예측 전체 리스트 조회
     * */
    @GetMapping("/getAllPredicts")
    public ApiResponse<List<PredictRiskTableResponse>> getAllPredicts () {
        try{
            return ApiResponse.success(predictionService.getAllPredicts());
        } catch (Exception e) {
            return ApiResponse.fail("회수 지연 예측 전체 리스트 조회 :::" + e.getMessage());
        }
    }

}
