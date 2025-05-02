package com.example.wehago.client.controller;

import com.example.wehago.client.dto.ClientEntity;
import com.example.wehago.client.dto.ClientRequestDto;
import com.example.wehago.client.dto.ClientResponseDto;
import com.example.wehago.client.mapper.ClientMapper;
import com.example.wehago.client.service.ClientService;
import com.example.wehago.common.ApiResponse;
import com.example.wehago.common.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/client")
@RestController
public class ClientController extends BaseController {

    private ClientMapper clientMapper;

    private ClientService clientService;

    public ClientController(ClientMapper clientMapper, ClientService clientService) {
        this.clientMapper = clientMapper;
        this.clientService = clientService;
    }

    // test
    @GetMapping("/id")
    public String getClientEntity() {
        ClientEntity clientEntity = clientMapper.findById(1);
        log.info(clientEntity.getName());
        return clientEntity.getEmail();
    }

    /**
     * 거래처 전체 조회
     */
    @GetMapping("/getAllClients")
    public ApiResponse<List<ClientResponseDto>> getAllClients() {
        List<ClientResponseDto> dtos = clientService.getAllClients();
        return success(dtos);
    }

    /**
     * 거래처 등록
     *
     * @param : client
     */
    @PostMapping("/insertClient")
    public ApiResponse<Void> insertClient(@RequestBody ClientRequestDto dto) {
        try {
            clientService.insertClient(dto);
            return success();
        } catch (Exception e) {
            return fail("거래처 등록 실패 : " + e.getMessage());
        }
    }

    /**
     * 거래처 정보 수정
     */
    @PostMapping("/updateClient")
    public ApiResponse<Void> updateClient(@RequestBody ClientRequestDto dto) {
        try {
            clientService.updateClient(dto);
            return success();
        } catch (Exception e) {
            return fail("거래처 등록 실패 : " + e.getMessage());
        }
    }

    /**
     * 거래처 삭제 업데이트
     */
    @PostMapping("/deleteClient")
    public ApiResponse<Void> deleteClient(@RequestBody ClientRequestDto dto) {
        try {
            clientService.deleteClient(dto);
            return success();
        } catch (Exception e) {
            return fail("거래처 삭제 실패 : " + e.getMessage());
        }
    }

}
