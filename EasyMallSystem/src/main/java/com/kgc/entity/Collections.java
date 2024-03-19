package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author: 欧洋宏
 * @create: 2024-03-19 15:00
 * 购物车类
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Collections {
    private Integer id;
    private Integer userId;
    private List<Product> products;
    private Double sumPrice;
}
