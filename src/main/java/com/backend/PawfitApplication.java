package com.backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.backend.repository.*")
public class PawfitApplication {

	public static void main(String[] args) {
		SpringApplication.run(PawfitApplication.class, args);
	}

}
