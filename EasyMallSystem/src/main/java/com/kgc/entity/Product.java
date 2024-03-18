package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 15:43
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private Integer stock;
    private Integer categoryLevelId;
    private Integer fileId;
    private Integer isDel;
    private Date createTime;

}
