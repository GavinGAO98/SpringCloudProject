package com.gavin.cloud.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentFallbackServiceImpl implements PaymentHystrixService{
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        return "-----PaymentFallbackservice fall back-paymentInfo_OK ,o(TT)o";
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        return "-----PaymentFallbackservice fall back-paymentInfo_Timeout ,o(TT)o";
    }
}
