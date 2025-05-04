package com.example.wehago.predict.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PredictionResponseDto {
    private Integer predictedDelay;
    private String riskLevel;
    private String comment;
}
