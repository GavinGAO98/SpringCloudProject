package com.gavin.cloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 重构各子模块的通用包，要求将聚合子模块install到仓库
 * 【进阶版】返回前端的通用的封装块
 * @author gavin
 * @param <T> 使用泛型表示返回前端的各种数据类型
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code; //Q：为什么要用包装类呢？
    private String message;
    private T data;

    public CommonResult(Integer code, String message){
        this(code, message, null);
    }
}
