package com.kgc.service;

import com.kgc.entity.*;

import java.util.List;

public interface OrderService {
    public Message getOrderProductByUserId(int userId);
    public Message addOrder(List<Product> productList,Address address,Double count,int[] ids);
    public Message addOrder1(List<Product> productList,Address address,Double count);

    public Message getOrderList(Page page);

    public Message delOrderById(int id);
    public Message delOrderByIdAndUserId(int id,int userId);

    /**
     * 拿到所有支付过的订单集合
     * @return
     */
    public Message getOrderListInPay();

    Message combineOrders(int masterOrder, int childOrder);

    public Message getOrderListByUserId(Page page);


    public Order getOrderById(int id);


}
