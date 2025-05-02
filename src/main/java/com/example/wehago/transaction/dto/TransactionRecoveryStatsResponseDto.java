package com.example.wehago.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRecoveryStatsResponseDto {
    private Long clientId;                // 거래처ID
    private String name;                    // 거래처명
    private Long totalCount;                // 총 건수
    private Long totalAmount;               // 총 거래금액
    private Long recoveredAmount;           // 총 회수금액
    private Double recoveredRate;           // 회수율
    private Long avgRecoveredDays;          // 평균 회수일
    private String createdAt;               // 통계 돌린 날짜(현재)
    private String month;                   // 통계 돌린 달

}
