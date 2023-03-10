package com.gavin.cloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer //一定要标明是Eureka服务端
public class EurekaMonoMain7005 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMonoMain7005.class, args);
    }
}