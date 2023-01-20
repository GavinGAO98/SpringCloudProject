package com.gavin.cloud.service;

import com.gavin.cloud.entities.Payment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 * 此处的Service接口和Dao接口方法完成一致
 * @author Gavin
 * @data 2023-01-16
 */
@Service
public interface PaymentService {
    public int addPayment(Payment payment);

    public Payment getPaymentById(@Param("id")Long id);
}
