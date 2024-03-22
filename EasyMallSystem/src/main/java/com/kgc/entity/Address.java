package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

/**
 * @author: 欧洋宏
 * @create: 2024-03-22 10:32
 * 地址类
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private Integer id;
    private Integer userId;
    private String address;
    private Date creatTime;
    private Integer isDefault;
    private String remark;
    private Integer isDel;

}
