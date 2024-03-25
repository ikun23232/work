package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

/**
 * @author: 欧洋宏
 * @create: 2024-03-20 10:23
 * 订单类
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private Integer userId;
    private String loginName;
    private String userAddress;
    private Date createTime;
    private Double cost;
    private String serialNumber;
    private Integer isDel;
    private Integer status;
}
