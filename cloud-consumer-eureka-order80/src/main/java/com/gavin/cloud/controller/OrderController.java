package com.gavin.cloud.controller;

import com.gavin.cloud.entities.CommonResult;
import com.gavin.cloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    //单节点服务时，先写死（固定）地址和端口
    //public static final String PAYMENT_URL = "http://localhost:8081";
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

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
    public CommonResult<Payment> getPaymentBody(@PathVariable("id") Long id){
        log.info("-------");
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get" + id, CommonResult.class);
    }

    /**
     * 使用ResponseEntity可以获得更详细的报文，如header、body主体
     * @param id
     * @return
     */
    @GetMapping("/consumer/payment/getForEntity/{id}")
    public CommonResult<Payment> getPaymentEntity(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);

        if(entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }else{
            return new CommonResult<>(444, "操作失败");
        }
    }
}
