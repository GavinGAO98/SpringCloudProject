package com.gavin.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaymentMain {

    //项目启动时只有@SpringBootApplication所在的包被被扫描
    public static void main(String[] args){
        SpringApplication.run(PaymentMain.class, args);
    }
}
