package com.gavin;

import com.gavin.MyRibbonConfig.MyRibbonRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "cloud-order-service", configuration= MyRibbonRule.class)
public class OrderMain {
    public static void main(String[] args){
        SpringApplication.run(OrderMain.class, args);
    }
}
