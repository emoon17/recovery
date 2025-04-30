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
        private String txId;
        private String clientId;
        private LocalDateTime txDate;
        private LocalDateTime dueDate;
        private LocalDateTime paidDate;
        private Long amount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
}
