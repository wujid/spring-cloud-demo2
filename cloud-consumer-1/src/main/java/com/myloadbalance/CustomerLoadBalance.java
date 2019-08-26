package com.myloadbalance;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;

/**
 * @Auther: wujid
 * @Date: 2019/8/24 12:08
 * @Description: 自定义算法
 */
public class CustomerLoadBalance {


    @Bean
    public IRule rule() {
        return new RandomRule();
    }
}
