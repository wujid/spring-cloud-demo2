package com.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.cloud.dao")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker
public class CloudProviderA1Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudProviderA1Application.class, args);
    }

}
