package com.example.wehago.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDto {

    private Long clientId;            // 거래처 고유 ID
    private String businessNumber;   // 사업자번호
    private String name;              // 거래처 이름
    private String industry;          // 거래처 업종
    private String email;             // 거래처 담당자 이메일
    private String contact;           // 거래처 연락처
    private Integer expectedRecoveryDays;  // 평균 회수 예상 일수
    private String memo;              // 메모
    private String delYn;             // 삭제 여부
    private LocalDateTime createdAt;   // 등록일시
    private LocalDateTime updatedAt;   // 수정일시


    //Entity -> dto
    public static ClientResponseDto fromEntity(ClientEntity entity) {
        ClientResponseDto dto = new ClientResponseDto();
        dto.setClientId(entity.getClientId());
        dto.setBusinessNumber(entity.getBusinessNumber());
        dto.setName(entity.getName());
        dto.setIndustry(entity.getIndustry());
        dto.setEmail(entity.getEmail());
        dto.setContact(entity.getContact());
        dto.setExpectedRecoveryDays(entity.getExpectedRecoveryDays());
        dto.setMemo(entity.getMemo());
        dto.setDelYn(entity.getDelYn());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }
}
