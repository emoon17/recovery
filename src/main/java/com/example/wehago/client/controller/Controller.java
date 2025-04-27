package com.example.wehago.client.controller;

import com.example.wehago.client.dto.ClientEntity;
import com.example.wehago.client.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private ClientMapper clientMapper;

    public Controller(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    @GetMapping("/test/id")
    public String getClientEntity() {
        ClientEntity clientEntity = clientMapper.findById(1);
        return clientEntity.getName();
    }

}
