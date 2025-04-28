package com.example.wehago.client.mapper;

import com.example.wehago.client.dto.ClientEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClientMapper {

    ClientEntity findById(@Param("id") int id);
    List<ClientEntity> selectAllClients();
}
