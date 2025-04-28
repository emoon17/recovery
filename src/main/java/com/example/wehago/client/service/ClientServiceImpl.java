package com.example.wehago.client.service;

import com.example.wehago.client.dto.ClientEntity;
import com.example.wehago.client.dto.ClientResponseDto;
import com.example.wehago.client.mapper.ClientMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientMapper clientMapper;

    public ClientServiceImpl(ClientMapper clientMapper) {
        this.clientMapper = clientMapper;
    }

    @Override
    public List<ClientResponseDto> getAllClients() {
        List<ClientEntity> clients = clientMapper.selectAllClients();
        return clients.stream()
                .map(ClientResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
