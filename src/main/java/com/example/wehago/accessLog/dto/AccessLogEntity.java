package com.example.wehago.accessLog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccessLogEntity {
    private int logId;
    private String ipAddress;
    private String userAgent;
    private String referrerUrl;
    private String createdAt;
}
