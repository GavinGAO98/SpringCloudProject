package com.gavin.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient //适用于各种类型的注册中心
public class GatewayMain9857 {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMain9857.class, args);
    }
}