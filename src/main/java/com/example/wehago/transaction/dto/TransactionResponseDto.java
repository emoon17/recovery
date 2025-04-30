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
    private String txId;            // 거래ID
    private String clientId;        // 거래처ID
    private String name;           // 거래처명
    private String businessNumber; // 거래처 사업자번호
    private LocalDateTime txDate;  // 거래 발생일
    private LocalDateTime dueDate; // 회수 예정일
    private LocalDateTime paidDate; // 실제 회수일(미회수 시 NULL)
    private Long amount;            // 금액
    private LocalDateTime createdAt;    // 등록일시
    private LocalDateTime updatedAt;

    public static TransactionResponseDto fromEntity(TransactionEntity entity) {
        TransactionResponseDto dto = new TransactionResponseDto();
        dto.setTxId(entity.getTxId());
        dto.setClientId(entity.getClientId());
        dto.setTxDate(entity.getTxDate());
        dto.setDueDate(entity.getDueDate());
        dto.setPaidDate(entity.getPaidDate());
        dto.setAmount(entity.getAmount());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;

    }
}
