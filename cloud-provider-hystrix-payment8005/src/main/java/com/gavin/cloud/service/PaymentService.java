package com.gavin.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {

    /**
     * 正常访问的方法
     * @param id
     * @return
     */
    public String paymentInfo_OK(Integer id){
        return "线程池: " + Thread.currentThread().getName() + " paymentInfo_OK, id: " + id.toString() + "\t"
         + "O(∩_∩)O";
    }

    //当服务自身中的此方法超出3秒没有返回结果时，则调用服务降级方法paymentInfo_Timeout_Fallback（Hystrix采用的是线程池执行服务降级方法，从而进行隔离）
    @HystrixCommand(fallbackMethod = "paymentInfo_Timeout_Fallback",
    commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_Timeout(Integer id){
        int timeout = 5000;
        //int i = 10 / 0;
        try{
            TimeUnit.MILLISECONDS.sleep(timeout);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return "线程池: " + Thread.currentThread().getName() + " paymentInfo_OK, id: " + id.toString() + "\t"
                + "O(∩_∩)O" + "耗时" + timeout + "秒";
    }

    /**
     * Hystrix服务降级方法
     * @param id
     * @return
     */
    public String paymentInfo_Timeout_Fallback(Integer id){
        return "线程池: " + Thread.currentThread().getName() + " paymentInfo_OK, id: " + id.toString() + "\t"
                + "/(ㄒoㄒ)/~~";
    }

    //主业务类
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否启动断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //失败率达到多少后熔断
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("*********id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功， 流水号：" + serialNumber;
    }

    /**
     * 服务熔断时调用的方法
     * @param id
     * @return
     */
    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id){

        return "id不能为负数，请稍后再试。" + id;
    }
}
