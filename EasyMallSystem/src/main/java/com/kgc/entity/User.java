package com.kgc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/17/ 19:18
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

  private int  id;
  private String  loginName;
  private String  userName;
  private String  password;
  private int  sex;
  private String  identityCode;
  private String  email;
  private String  mobile;
  private int  type;

}
