package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/20/ 15:27
 * @Description
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class History {

   private int id;
   private int userId;
   private int productId;
   private int isDel;
   private Date createTime;
}
