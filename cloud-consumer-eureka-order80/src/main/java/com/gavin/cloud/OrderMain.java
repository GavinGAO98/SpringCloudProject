package com.gavin.cloud;

import com.gavin.myRibbonConfig.MyRibbonRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//Spring cloud提供的声明Ribbon配置注解
@RibbonClient(name = "cloud-order-service", configuration= MyRibbonRule.class)
public class OrderMain {
    public static void main(String[] args){
        SpringApplication.run(OrderMain.class, args);
    }
}
