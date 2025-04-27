package com.example.wehago.client.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientEntity {

    private Long clientId;            // 거래처 고유 ID
    private String name;              // 거래처 이름
    private String industry;          // 거래처 업종
    private String email;             // 거래처 담당자 이메일
    private String contact;           // 거래처 연락처
    private Integer expectedRecoveryDays;  // 평균 회수 예상 일수
    private String memo;              // 메모
    private LocalDateTime createdAt;   // 등록일시
    private LocalDateTime updatedAt;   // 수정일시
}
