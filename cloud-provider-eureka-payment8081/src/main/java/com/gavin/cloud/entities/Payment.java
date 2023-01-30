package com.gavin.cloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gavin
 * @create 2023-01-14
 * 主实体Payment，还要有一个包含传递给前端的JSON封装体
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private long id;
    protected String serial_no;

    public void setterId(long id){
        this.id = id;
    }
}
