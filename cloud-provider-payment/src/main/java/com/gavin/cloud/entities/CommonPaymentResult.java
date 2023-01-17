package com.gavin.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 【基础版】只表示Payment结果的前端封装实体类
 * @author gavin
 * @date 2023-01-14
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonPaymentResult {
//    private long id;
//    private String serial_no;
    private Integer code; //表示状态码
    private String message;
    private Payment payment; //封装了实体类payment
}
