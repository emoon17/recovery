package com.example.wehago.client.service;

import com.example.wehago.client.dto.ClientEntity;
import com.example.wehago.client.dto.ClientRequestDto;
import com.example.wehago.client.dto.ClientResponseDto;
import com.example.wehago.client.mapper.ClientMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.*;

public class TransactionServiceImplTest {

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientServiceImpl clientService;

    public TransactionServiceImplTest() {
        MockitoAnnotations.initMocks(this); // Mock, InjeckMocks활성화
    }

    /**
     * 거래처 전체 조회
     * */
    @Test
    void getAllClient_success() {
        // given
        List<ClientEntity> mockClients = Arrays.asList(
                ClientEntity.builder()
                        .clientId(1L)
                        .businessNumber("232423423424")
                        .name("천재교육")
                        .industry("교육")
                        .email("edu@chunjae.co.kr")
                        .contact("02-1234-5678")
                        .expectedRecoveryDays(30)
                        .memo("aaa")
                        .build(),

                ClientEntity.builder()
                        .clientId(2L)
                        .businessNumber("765765757")
                        .name("네이버")
                        .industry("IT")
                        .email("naver@naver.com")
                        .contact("031-1234-5678")
                        .expectedRecoveryDays(15)
                        .memo("dddd")
                        .build()
        );
        when(clientMapper.selectAllClients()).thenReturn(mockClients);

        //when
        List<ClientResponseDto> clients = clientService.getAllClients(); // 실제 테스트할 메소드

        // then
//        assertThat(clients).isEqualTo(mockClients);
        assertThat(clients).hasSize(2);
        assertThat(clients.get(0).getClientId()).isEqualTo(1L);

        System.out.println("result");
        clients.forEach(c -> System.out.println(c.getName() + " - " + c.getIndustry()));
    }

    /**
     * clinet insert
     * */
    @Test
    void insertClient_success() {
        ClientRequestDto client = ClientRequestDto.builder()
                .businessNumber("123-45-78945")
                .name("더존비즈온")
                .industry("회계/IT")
                .email("dz@dz.com")
                .contact("010-4545-4545")
                .expectedRecoveryDays(30)
                .memo("붙고싶다!")
                .build();

        //when
        clientService.insertClient(client);

        //then
      //  verify(clientMapper, times(1)).insertClient(client);
    }
}
