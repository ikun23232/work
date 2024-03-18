package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 14:47
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private Integer parentID;
    private Integer type;
    private Integer isDel;
    private Date createTime;

}
