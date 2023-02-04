package com.gavin.cloud.controll;

import com.gavin.cloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@DefaultProperties(defaultFallback = "globalFallback")
@Slf4j
public class OrderHystrixController {
    @Autowired
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_oK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

    //消费者服务调用支付服务，但当订单侧服务超时1.5秒后仍未得到响应，则认为服务失效，进行服务降级，调用的是该服务自身的服务降级方法
    /*缺点：业务方法与服务降级方法耦合；且每个业务方法都对应一个服务降级方法，导致代码膨胀*/
    @HystrixCommand(fallbackMethod = "paymentTimeoutFallBack", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }

    public String paymentTimeoutFallBack(@PathVariable("id")Integer id){
        return "当前消费者服务80正常运行，调用的其他支付系统繁忙";
    }

    @HystrixCommand //注解没有属性，则调用全局服务降级方法
    @GetMapping("/consumer/payment/hystrix/exception/{id}")
    public String paymentInfo_RuntimeException(@PathVariable("id") Integer id){
        int i = 10 / 0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }


    /**
     * 全局（默认通用）服务降级方法
     * 要求降级方法与原业务方法参数列表必须相同
     * @param id
     * @return
     */
    public String globalFallback(@PathVariable("id") Integer id){
        return "当前服务的全局通用业务降级方法";
    }
}
