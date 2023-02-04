package com.gavin.cloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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
}
