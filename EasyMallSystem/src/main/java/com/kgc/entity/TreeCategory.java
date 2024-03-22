package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author: 欧洋宏
 * @create: 2024-03-21 22:39
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TreeCategory {
    private Integer value;
    private String label;
    private Object[] change;

}
