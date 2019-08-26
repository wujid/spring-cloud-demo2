package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//basePackages为必要属性
@EnableFeignClients(basePackages = "com.cloud")
public class CloudConsumerA1Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerA1Application.class, args);
    }

}
