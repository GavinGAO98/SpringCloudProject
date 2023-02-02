package com.gavin.cloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    Logger.Level setFeignLoggerLevel(){
        return Logger.Level.FULL;
    }
}
