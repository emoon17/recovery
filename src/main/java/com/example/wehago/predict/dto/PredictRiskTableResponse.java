package com.example.wehago.predict.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PredictRiskTableResponse {

    private Long clientId;
    private Long txId;
    private String name;
    private Integer predictedDelay;      // 예측 지연일수
    private String transactionDate;      // 거래 일자
    private Integer realDelay;           // 실제 지연일수
    private Integer absError;           // 오차범위
    private String riskLevel;           // 위험레벨
    private String comment;
    private String createdAt;
}
