package com.samples.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SamplesNacosGateWayConsumerExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SamplesNacosGateWayConsumerExampleApplication.class, args);
    }

}
