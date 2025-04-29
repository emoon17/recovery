package com.example.wehago.client.service;


import com.example.wehago.client.dto.ClientEntity;
import com.example.wehago.client.dto.ClientRequestDto;
import com.example.wehago.client.dto.ClientResponseDto;

import java.util.List;

public interface ClientService {

    List<ClientResponseDto> getAllClients();
    void insertClient(ClientRequestDto dto);
    void updateClient(ClientRequestDto dto);
    void deleteClient(ClientRequestDto dto);
}
