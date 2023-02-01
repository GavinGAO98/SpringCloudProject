package com.gavin.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import java.io.File;

@SpringBootApplication
@EnableEurekaServer //一定要标明是Eureka服务端
public class EurekaMain7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaMain7001.class, args);
        //打印项目的根目录：D:\★Back-endDesign\Back-endFramework\Frameworks\WorkSpace\IdeaWorkSpace\SpringCloudWs
        File file = new File("./");
        System.out.println(file.getAbsoluteFile());
    }

}
