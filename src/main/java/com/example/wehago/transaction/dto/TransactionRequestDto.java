package com.example.wehago.transaction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionRequestDto {
    private String transactionId;               // 거래ID
    private String clientId;                    // 거래처ID
    private String name;                        // 거래처명
    private String businessNumber;
    private String transactionDate;             // 거래 발생일
    private Long transactionAmount;
    private String expectedPaymentDate;
    private String recoveredDate;               // 실제 회수일(미회수 시 NULL)
    private Long recoveredAmount;               // 실제 회수 금액

}
