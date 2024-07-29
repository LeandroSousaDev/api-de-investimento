package com.leandroProject.apideinvestimentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApideinvestimentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApideinvestimentosApplication.class, args);
	}

}
