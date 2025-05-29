package com.example.wehago.predict.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PredictionTargetDto {
    private Long txId;
    private Long clientId;
    private String name;
    private Integer transactionAmount;
    private String industry;
    private Integer expectedRecoveryDays;
    private Integer realDelay;
}
