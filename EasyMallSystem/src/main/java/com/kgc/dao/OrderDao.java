package com.kgc.dao;

import com.kgc.entity.Order;
import com.kgc.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    public List<Product> getOrderProductByUserId(int userId);

    public int addOrderProductByList(Order order);

    public int addOrderProductBytwo(@Param("orderId") int id, @Param("productId") int productId, @Param("quantity") int quantity, @Param("cost") double cost);

    public int addOrder(Order order);

    public List<Order> getOrderList();

    public int delOrder(int id);
    /**
     *根据我的orderId拿到所有商品集合
     */
    public List<Product> getProductListByOrderId(int orderId);


    public int updateStatusById(String serialNumber);

    /**
     * 拿到所有以及支付的订单
     */
    public List<Order> getOrderListInPay(@Param("userId") int userId);

    /**
     * 根据我的订单号查出所有的产品信息
     * @param serialNumber
     * @return
     */
    public List<Product> getProductListByserialNumber(String serialNumber);


    /**
     *
     */
    public Order getOrderById(int id);


    public int updateOrderByContail(@Param("cost")double cost,@Param("id")int id);

    public  int updateOrderDetailByProductId(@Param("quantity")int quantity,@Param("productId")int productId,@Param("orderId") int orderId);

    public int delOrderProductByOrderId(int orderId);
}
