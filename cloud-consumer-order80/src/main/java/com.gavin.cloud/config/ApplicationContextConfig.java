package com.gavin.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 使用Spring的标准类配置bean
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    //相当于在applicationContext.xml中使用元素<bean id="" class="">
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
