package com.example.wehago.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity {
        private String transactionId;
        private String clientId;
        private String transactionDate;
        private Long transactionAmount;
        private String expectedPaymentDate;
        private String recoveredDate;
        private Long recoveredAmount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
}
