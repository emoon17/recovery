package com.example.wehago.client.service;


import com.example.wehago.client.dto.ClientEntity;
import com.example.wehago.client.dto.ClientResponseDto;

import java.util.List;

public interface ClientService {

    List<ClientResponseDto> getAllClients();
}
