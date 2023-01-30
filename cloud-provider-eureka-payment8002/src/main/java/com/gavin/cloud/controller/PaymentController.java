package com.gavin.cloud.controller;

import com.gavin.cloud.entities.CommonResult;
import com.gavin.cloud.entities.Payment;
import com.gavin.cloud.service.PaymentService;
import com.netflix.appinfo.InstanceInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    //调用service层接口
    //@Autowired 为啥用不了这个注释？
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;

    //用于Eureka服务发现
    @Autowired
    //@Resource // 用@Autowired不能自动注入com.netflix.discovery.DiscoveryClient类
    private DiscoveryClient discoveryClient; //有两个同名DiscoveryClient类/接口

    /**
     *
     * @param payment
     * @return 返回到前端的是实体封装类
     */
    @PostMapping("/payment/create") //为了体现REST风格，不建议用@Mapping(METHOD=)
    public CommonResult createPayment(Payment payment){
        int result = paymentService.addPayment(payment);

        //添加一行日志 Q:能否用AOP实现？
        //log.info("*****插入结果：" + result);

        if(result > 0){
            return new CommonResult(200,  serverPort + " Insert successfully!", result); //Q:为什么不声明泛型的类型参数？
        }else{
            return new CommonResult(404, "Insert failed!", null);
        }
    }

    @GetMapping(value="/payment/get/{id}")
    public CommonResult selectPaymentById(@PathVariable("id")long id){
        Payment payment = paymentService.getPaymentById(id);

        //log.info("*****查询结果" + payment);

        if(payment != null){
            return new CommonResult<>(200, serverPort + " Select Successfully!", payment);
        }else{
            return new CommonResult<>(404, "Select " + id + " failed!, No record!", null);
        }
    }

    @GetMapping("/payment/discovery") //Q：为什么discovery后面没有反斜杠？
    public Object getEurekaDiscovery(){
        List<String> services = discoveryClient.getServices();
        for(String service: services){
            log.info("*****element: " + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE"); //Q：为什么写死了参数？
        for(ServiceInstance instance : instances){
            log.info(instance.getInstanceId() + "\t" + instance.getHost() + "\t"+instance.getPort() + "\t" + instance.getUri());
        }
        return discoveryClient;
    }
}

