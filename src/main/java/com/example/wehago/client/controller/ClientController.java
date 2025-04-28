package com.example.wehago.client.controller;

import com.example.wehago.client.dto.ClientEntity;
import com.example.wehago.client.dto.ClientResponseDto;
import com.example.wehago.client.mapper.ClientMapper;
import com.example.wehago.client.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/api/client")
@RestController
public class ClientController {

    private ClientMapper clientMapper;

    private ClientService clientService;

    public ClientController(ClientMapper clientMapper, ClientService clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    @GetMapping("/id")
    public String getClientEntity() {
        ClientEntity clientEntity = clientMapper.findById(1);
        log.info(clientEntity.getName());
        return clientEntity.getEmail();
    }

    @GetMapping("/getAllClients")
    public List<ClientResponseDto> getAllClients(){
        return clientService.getAllClients();
    }

    @PostMapping("/insertClient")
    public void insertClient(){

    }

}
