package com.example.microserviciosaxemenes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviciosAxemenesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosAxemenesApplication.class, args);
	}

}
