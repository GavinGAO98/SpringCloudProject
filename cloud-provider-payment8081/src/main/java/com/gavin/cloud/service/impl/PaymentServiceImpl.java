package com.gavin.cloud.service.impl;

import com.gavin.cloud.dao.PaymentDao;
import com.gavin.cloud.entities.Payment;
import com.gavin.cloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * an implement of payment Service
 * @author Gavin
 */
@Service //Q：为什么只要在接口实现类前加注解，而不是在接口前加注解？
public class PaymentServiceImpl implements PaymentService {
    @Resource //java自带的自动填充注解
    private PaymentDao paymentDao; //调用dao层接口，避免直接操作数据库

    public int addPayment(Payment payment){
        return paymentDao.addPayment(payment);
    }

    public Payment getPaymentById(Long id){
        return getPaymentById(id);
    }
}
