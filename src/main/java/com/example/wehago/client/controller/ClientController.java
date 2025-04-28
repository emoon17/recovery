package com.example.wehago.client.controller;

import com.example.wehago.client.dto.ClientEntity;
import com.example.wehago.client.mapper.ClientMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ClientController {

    private ClientMapper clientMapper;

    public ClientController(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    @GetMapping("/test/id")
    public String getClientEntity() {
        ClientEntity clientEntity = clientMapper.findById(1);
        log.info(clientEntity.getName());
        return clientEntity.getEmail();
    }

}
