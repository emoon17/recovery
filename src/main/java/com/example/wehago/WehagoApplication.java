package com.example.wehago;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.wehago.client.mapper")
public class WehagoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WehagoApplication.class, args);
	}

}
