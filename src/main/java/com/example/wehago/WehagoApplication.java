package com.example.wehago;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WehagoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WehagoApplication.class, args);
	}

}
