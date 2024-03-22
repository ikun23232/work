package com.kgc.service.impl;

import com.kgc.dao.CollectionDao;
import com.kgc.dao.OrderDao;
import com.kgc.dao.ProductDao;
import com.kgc.entity.Address;
import com.kgc.entity.Message;
import com.kgc.entity.Order;
import com.kgc.entity.Product;
import com.kgc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: 欧洋宏
 * @create: 2024-03-20 10:27
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CollectionDao collectionDao;

    @Override
    public Message getOrderProductByUserId(int userId) {
        List<Product> orderProductByUserId = orderDao.getOrderProductByUserId(userId);
        return Message.success(orderProductByUserId);
    }

    @Override
    public Message addOrder(List<Product> productList,Address address, Double count, int[] ids) {
        //查看库存
        if (productList==null||address==null||ids==null){
            return Message.error("参数错误....");
        }
        for (Product product : productList) {
            int productStockById = productDao.getProductStockById(product.getId());
            if (productStockById-product.getQuantity()<=5){
                return Message.error(product.getName()+"的库存不够呐.....");
            }
        }

        //先创建order表
//        Map map=new HashMap<>();
//        map.put("address",address);
//        map.put("cost",count);
//        //用户名 id写死
//        map.put("userId","22");
//        map.put("loginName","add");
        Order order=new Order();
        order.setUserAddress(address.getAddress());
        order.setCost(count);
        order.setUserId(22);
       order.setLoginName("add");
        int orderId = orderDao.addOrder(order);

        //拿到orderId后添加order_Temp表
        for (Product product : productList) {
            int i = orderDao.addOrderProductBytwo(order.getId(),product.getId(), product.getQuantity(), product.getQuantity()*product.getPrice());
        }

        //先减去商品的库存先
        for (Product product : productList) {
            int i = productDao.updateProductStockById(product.getQuantity(), product.getId());
        }
        //减去购物车中的商品
        for (Product product : productList) {
            int i =collectionDao.delProductInCarById(product.getId());
        }

        //回调支付宝

        return Message.success("添加成功");


    }

}
