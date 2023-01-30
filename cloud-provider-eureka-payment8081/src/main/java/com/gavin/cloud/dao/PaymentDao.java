package com.gavin.cloud.dao;

import com.gavin.cloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper //建议使用ibatis注解@Mapper，而非使用Spring的注解@Repostory，后者使用时可能会出现问题
public interface PaymentDao {
    public int addPayment(Payment payment);

    public Payment getPaymentById(@Param("id")Long id);
}
