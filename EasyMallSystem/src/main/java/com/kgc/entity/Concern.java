package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/24/ 12:03
 * @Description
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Concern {

 private int id;
 private int userId;
 private int productId;
 private Date createTime;
 private int isDel;

}
