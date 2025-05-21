package com.example.wehago.accessLog.mapper;

import com.example.wehago.accessLog.dto.AccessLogEntity;
import org.apache.ibatis.annotations.Mapper;
import reactor.netty.http.server.HttpServerRequest;

@Mapper
public interface AccessLogMapper {
    void insertAccessLog(AccessLogEntity accessLogEntity);
}
