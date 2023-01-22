package com.gavin.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient //入驻Eureka
public class PaymentMain {

    //项目启动时只有@SpringBootApplication所在的包被被扫描
    public static void main(String[] args){
        SpringApplication.run(PaymentMain.class, args);
    }
}
