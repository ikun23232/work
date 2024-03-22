package com.kgc.service;

import com.kgc.entity.Address;
import com.kgc.entity.Message;
import com.kgc.entity.Order;
import com.kgc.entity.Product;

import java.util.List;

public interface OrderService {
    public Message getOrderProductByUserId(int userId);
    public Message addOrder(List<Product> productList,Address address,Double count,int[] ids);
}
