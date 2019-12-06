package com.jobs.board.listings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class JobsListingsApplication {
	public static void main(String[] args) {
		SpringApplication.run(JobsListingsApplication.class, args);
	}
}
