package com.example.wehago.predict.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredictionRiskEntity {
    private Long predictionId;
    private Long clientId;
    private Long txId;
    private String transactionDate;
    private String clientName;
    private Integer predictedDelay;     // 예측된 지연 일수
    private Integer realDelay;          // 실제 지연 일수
    private Integer absError;
    private String riskLevel;           // 위험도 수준 (LOW/MEDIUM/HIGH)
    private String comment;
    private String createdAt;
    private String updatedAt;

}
