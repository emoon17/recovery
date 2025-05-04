package com.example.wehago.predict.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/predict")
public class PredictController {

    private final WebClient webClient;

    public PredictController(WebClient webClient) {
        this.webClient = webClient;
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

}
