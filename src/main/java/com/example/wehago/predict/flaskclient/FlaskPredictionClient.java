package com.example.wehago.predict.flaskclient;

import com.example.wehago.predict.dto.PredictionResponseDto;
import com.example.wehago.predict.dto.PredictionTargetDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class FlaskPredictionClient {

    private final WebClient flaskWebClient;

    public FlaskPredictionClient(WebClient flaskWebClient) {
        this.flaskWebClient = flaskWebClient;
    }
    
    public PredictionResponseDto predict(PredictionTargetDto target) {
        return flaskWebClient.post()
                .uri("/ml/predict")
                .bodyValue(target)
                .retrieve()
                .bodyToMono(PredictionResponseDto.class)
                .block(); // 동기처리
                
    }

    public void train() {
        flaskWebClient.post()
                .uri("/ml/train")
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
    
}
