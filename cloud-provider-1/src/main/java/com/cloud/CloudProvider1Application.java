package com.cloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.cloud.dao")
@EnableEurekaClient
@EnableDiscoveryClient
public class CloudProvider1Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudProvider1Application.class, args);
    }

}
