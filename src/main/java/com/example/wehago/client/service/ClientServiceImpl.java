package com.example.wehago.client.service;

import com.example.wehago.client.dto.ClientEntity;
import com.example.wehago.client.dto.ClientRequestDto;
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

    @Override
    public void insertClient(ClientRequestDto dto) {
        ClientEntity clientEntity = ClientEntity.builder()
                .businessNumber(dto.getBusinessNumber())
                .name(dto.getName())
                .industry(dto.getIndustry())
                .email(dto.getEmail())
                .contact(dto.getContact())
                .expectedRecoveryDays(dto.getExpectedRecoveryDays())
                .memo(dto.getMemo())
                .build();

        clientMapper.insertClient(clientEntity);
    }

    @Override
    public void updateClient(ClientRequestDto dto) {
        ClientEntity clientEntity = ClientEntity.builder()
                .clientId(dto.getClientId())
                .businessNumber(dto.getBusinessNumber())
                .name(dto.getName())
                .industry(dto.getIndustry())
                .email(dto.getEmail())
                .contact(dto.getContact())
                .expectedRecoveryDays(dto.getExpectedRecoveryDays())
                .memo(dto.getMemo())
                .build();
        clientMapper.updateClient(clientEntity);
    }

    @Override
    public void deleteClient(ClientRequestDto dto) {
        ClientEntity clientEntity = ClientEntity.builder()
                .clientId(dto.getClientId())
                .delYn("Y")
                .build();
        clientMapper.deleteClient(clientEntity);
    }

    public int selectExpectedRecoveryDaysByClientId(String clientId) {
        return clientMapper.selectExpectedRecoveryDaysByClientId(clientId);
    }
}
