package com.example.wehago.accessLog.service;

import com.example.wehago.accessLog.dto.AccessLogEntity;
import com.example.wehago.accessLog.mapper.AccessLogMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.netty.http.server.HttpServerRequest;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    private AccessLogMapper accessLogMapper;

    public AccessLogServiceImpl(AccessLogMapper accessLogMapper) {
        this.accessLogMapper = accessLogMapper;
    }

    @Transactional
    public void insertAccessLog(HttpServletRequest request){
        String ip = getClientIp(request);
        String userAgent = (request).getHeader("User-Agent");
        String referer = (request).getHeader("Referer");

        AccessLogEntity accessLog = AccessLogEntity.builder()
                .ipAddress(ip)
                .userAgent(userAgent)
                .referrerUrl(referer)
                .build();
        accessLogMapper.insertAccessLog(accessLog);

    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip)) {
            return ip.split(",")[0];
        }
        return request.getRemoteAddr();
    }
}
