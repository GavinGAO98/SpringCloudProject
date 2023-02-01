package com.gavin.cloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 使用Spring的标准类配置bean
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    /*@LoadBalanced //该注解启用了Ribbon负载均衡
    注释该注解的目的是测试自定义的负载均衡策略能否剩下
    */
    //相当于在applicationContext.xml中使用元素<bean id="" class="">
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
