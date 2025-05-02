package com.example.wehago.recovery.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecoveryStatEntity {
    private Long statId;                      // 통계 id
    private Long clientId;                    // 거래처 id
    private String month;                    // 월
    private Long totalAmount;               // 전체 거래 금액
    private Long recoveredAmount;           // 총 회수금액
    private Double recoveredRate;           // 회수율
    private Long avgRecoveredDays;          // 평균 회수일
    private String createdAt;
    private String updatedAt;
}
