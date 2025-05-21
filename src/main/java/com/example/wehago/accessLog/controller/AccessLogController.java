package com.example.wehago.accessLog.controller;

import com.example.wehago.accessLog.service.AccessLogService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.netty.http.server.HttpServer;
import reactor.netty.http.server.HttpServerRequest;

@RestController
@RequestMapping("/api/log")
public class AccessLogController {

    private final AccessLogService accessLogService;

    public AccessLogController(AccessLogService accessLogService) {
        this.accessLogService = accessLogService;
    }

    @PostMapping("/access")
    public void insertAccessLog(HttpServletRequest request) {
        accessLogService.insertAccessLog(request);
    }
}
