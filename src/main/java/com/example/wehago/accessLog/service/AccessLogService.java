package com.example.wehago.accessLog.service;

import jakarta.servlet.http.HttpServletRequest;
import reactor.netty.http.server.HttpServerRequest;

public interface AccessLogService {
    void insertAccessLog(HttpServletRequest request);
}
