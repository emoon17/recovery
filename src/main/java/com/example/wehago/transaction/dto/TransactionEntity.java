package com.example.wehago.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity {
        private String transactionId;
        private String clientId;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private String transactionDate;
        private Long transactionAmount;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private String expectedPaymentDate;
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private String recoveredDate;
        private Long recoveredAmount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
}
