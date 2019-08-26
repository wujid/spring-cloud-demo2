package com.cloud;

import com.myloadbalance.CustomerLoadBalance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//指定哪个服务使用哪种自定义算法
@RibbonClient(name = "provider-user", configuration = CustomerLoadBalance.class)
public class CloudConsumer1Application {

    public static void main(String[] args) {
        SpringApplication.run(CloudConsumer1Application.class, args);
    }

}
