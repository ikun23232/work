package com.kgc.service;

import com.kgc.entity.Message;

import java.util.Map;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/19/ 10:35
 * @Description
 */
public interface AlipayService {

    public Message createOrder(int amount,String orderName,String serialNumber);

    public Message backOrder(Map params);
}
