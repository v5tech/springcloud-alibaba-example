package com.samples.integration.call;

import com.alibaba.cloud.dubbo.annotation.DubboTransported;
import com.samples.integration.common.dto.CommodityDTO;
import com.samples.integration.common.dto.OrderDTO;
import com.samples.integration.common.response.ObjectResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SamplesBusinessServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SamplesBusinessServiceApplication.class, args);
	}

	@Bean
	// @LoadBalanced
	@DubboTransported
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}



}
