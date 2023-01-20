package com.gavin.cloud.controller;

import com.gavin.cloud.entities.CommonResult;
import com.gavin.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 订单微服务只需要controller层，不用写入数据库？
 * @author gavin
 * @data 2023-01-20
 */

@RestController
@Slf4j
public class OrderController {

    //先固定地址和端口
    public static final String PAYMENT_URL = "http://localhost:8081";

    //使用Spring提供的restTemplate访问restful接口
    @Autowired
    private RestTemplate restTemplate;

    //Q:为什么是GET方法？
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> createOrderResult(Payment payment){
        log.info("----"); //Q：能否用事务实现？
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        log.info("-------");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get" + id, CommonResult.class);
    }
}
