package com.gavin.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringApplicationBean {

    @Bean
    @LoadBalanced
    //【基础版】使用最简单的HTTP调用方式RestTemplate
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
