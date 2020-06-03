package com.samples.integration.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.samples.integration.order.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class SamplesOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SamplesOrderServiceApplication.class, args);
	}

}
