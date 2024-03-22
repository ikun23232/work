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

}
