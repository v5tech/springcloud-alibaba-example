package com.samples.integration.storage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan("com.samples.integration.storage.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class SamplesStorageServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SamplesStorageServiceApplication.class, args);
	}

}
