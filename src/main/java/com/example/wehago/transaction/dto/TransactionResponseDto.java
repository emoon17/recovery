package com.example.wehago.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionResponseDto {
    private String transactionId;            // 거래ID
    private String clientId;        // 거래처ID
    private String name;           // 거래처명
    private String businessNumber; // 거래처 사업자번호
    private String transactionDate;  // 거래 발생일
    private Long transactionAmount;            //거래 금액
    private String expectedPaymentDate; // 회수 예정일
    private String recoveredDate; // 실제 회수일(미회수 시 NULL)
    private Long recoveredAmount;        // 실제 회수 금액
    private LocalDateTime createdAt;    // 등록일시
    private LocalDateTime updatedAt;

    public static TransactionResponseDto fromEntity(TransactionEntity entity) {
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.transactionId = entity.getTransactionId();
        dto.clientId = entity.getClientId();
        dto.transactionDate = entity.getTransactionDate();
        dto.transactionAmount = entity.getTransactionAmount();
        dto.expectedPaymentDate = entity.getExpectedPaymentDate();
        dto.recoveredDate = entity.getRecoveredDate();
        dto.recoveredAmount = entity.getRecoveredAmount();
        dto.createdAt = entity.getCreatedAt();
        dto.updatedAt = entity.getUpdatedAt();

        return dto;

    }
}
