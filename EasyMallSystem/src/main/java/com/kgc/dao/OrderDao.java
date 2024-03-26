package com.kgc.dao;

import com.kgc.entity.Order;
import com.kgc.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    public List<Product> getOrderProductByUserId(int userId);

    public int addOrderProductByList(Order order);

    public int addOrderProductBytwo(@Param("orderId") int id,@Param("productId") int productId,@Param("quantity") int quantity,@Param("cost") double cost);

public int addOrder(Order order);

    public List<Order> getOrderListByALL(int roleId);

    public int delOrder(int id);
    /**
     *根据我的orderId拿到所有商品集合
     */
    public List<Product> getProductListByOrderId(int orderId);


    public int updateStatusById(String serialNumber);

    /**
     * 拿到所有以及支付用户的订单
     */
    public List<Order> getOrderListInPay(@Param("userId") int userId);

    /**
     * 根据我的订单号查出所有的产品信息
     * @param serialNumber
     * @return
     */
    public List<Product> getProductListByserialNumber(@Param("serialNumber")String serialNumber,@Param("userId")int userId);


    /**
     *
     */
    public Order getOrderById(int id);


    public int updateOrderByContail(@Param("cost")double cost,@Param("id")int id);

    public  int updateOrderDetailByProductId(@Param("quantity")int quantity,@Param("productId")int productId,@Param("orderId") int orderId);

    public int delOrderProductByOrderId(int orderId);
    /**
     * 查看用户未付款的订单
     * @param userId
     * @return
     */
    public List<Order> getOrderByUserIdByStaus(int userId);

    List<Order> getOrderListByUserId(int userId);

    List<Order>  getOrderListInPayByALL(int roleId);
}
