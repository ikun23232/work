package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 13:51
 **/
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private Integer id;
    private String title;
    private String content;
    private Date createTime;
}
