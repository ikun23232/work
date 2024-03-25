package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CollectionDao;
import com.kgc.dao.OrderDao;
import com.kgc.dao.ProductDao;
import com.kgc.entity.*;
import com.kgc.service.OrderService;
import com.kgc.utils.TimedtasksUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    private TimedtasksUtil timedtasksUtil;



    @Override
    public Message getOrderProductByUserId(int userId) {
        List<Product> orderProductByUserId = orderDao.getOrderProductByUserId(userId);
        return Message.success(orderProductByUserId);
    }

    @Override
    public Message addOrder(List<Product> productList, Address address, Double count, int[] ids) {
        //查看库存
        if (productList == null || address == null || ids == null) {
            return Message.error("参数错误....");
        }
        for (Product product : productList) {
            int productStockById = productDao.getProductStockById(product.getId());
            if (productStockById - product.getQuantity() <= 5) {
                return Message.error(product.getName() + "的库存不够呐.....");
            }
        }

        Order order = new Order();
        order.setUserAddress(address.getAddress());
        order.setCost(count);
        order.setUserId(22);
        order.setLoginName("add");
        order.setSerialNumber(UUID.randomUUID().toString());
        int orderId = orderDao.addOrder(order);

        //拿到orderId后添加order_Temp表
        for (Product product : productList) {
            int i = orderDao.addOrderProductBytwo(order.getId(), product.getId(), product.getQuantity(), product.getQuantity() * product.getPrice());
        }

        //先减去商品的库存先
        for (Product product : productList) {
            int i = productDao.updateProductStockById(product.getQuantity(), product.getId());
        }
        //减去购物车中的商品
        for (Product product : productList) {
            int i = collectionDao.delProductInCarById(product.getId());
        }

        //订单创建成功开启我的定时任务30分钟未支付自动删除改订单

        timedtasksUtil.startOrderTimeoutTask(String.valueOf(order.getId()),System.currentTimeMillis());

        //回调支付宝
        return Message.success(order.getSerialNumber());


    }
    public Message addOrder1(List<Product> productList, Address address, Double count) {
        //查看库存
        if (productList == null || address == null ) {
            return Message.error("参数错误....");
        }
        for (Product product : productList) {
            int productStockById = productDao.getProductStockById(product.getId());
            if (productStockById - product.getQuantity() <= 5) {
                return Message.error(product.getName() + "的库存不够呐.....");
            }
        }

        Order order = new Order();
        order.setUserAddress(address.getAddress());
        order.setCost(count);
        order.setUserId(22);
        order.setLoginName("add");
        order.setSerialNumber(UUID.randomUUID().toString());
        int orderId = orderDao.addOrder(order);

        //拿到orderId后添加order_Temp表
        for (Product product : productList) {
            int i = orderDao.addOrderProductBytwo(order.getId(), product.getId(), product.getQuantity(), product.getQuantity() * product.getPrice());
        }

        //先减去商品的库存先
        for (Product product : productList) {
            int i = productDao.updateProductStockById(product.getQuantity(), product.getId());
        }
        //减去购物车中的商品
        for (Product product : productList) {
            int i = collectionDao.delProductInCarById(product.getId());
        }

        //订单创建成功开启我的定时任务30分钟未支付自动删除改订单

        timedtasksUtil.startOrderTimeoutTask(String.valueOf(order.getId()),System.currentTimeMillis());

        //回调支付宝
        return Message.success(order.getSerialNumber());


    }

    @Override
    public Message getOrderList(Page page) {
        PageHelper.startPage(page.getCurrentPageNo(), page.getPageSize());
        List<Order> orderList = orderDao.getOrderList();
        PageInfo pageInfo=new PageInfo<>(orderList);
        return Message.success(pageInfo);
    }

    @Override
    public Message delOrderById(int id) {
        //删除订单
        int order = orderDao.delOrder(id);
        if (order<=0){
            return Message.error("删除失败");
        }
        //回我订单的库存
        //先拿我订单的商品集合
        List<Product> productListByOrderId = orderDao.getProductListByOrderId(id);
        for (Product product : productListByOrderId) {
            int stock=product.getQuantity();
            //分别加库存
            productDao.addStockById(product.getId(),stock);
        }
        return Message.success("订单删除成功");
    }

    @Override
    public Message getOrderListInPay(int userId) {

        List<Order> orderListInPay = orderDao.getOrderListInPay(userId);
        return Message.success(orderListInPay);
    }

    @Override
    @Transactional
    public Message combineOrders(int masterOrder, int childOrder) {
        //通过子订单拿到商品集合
        List<Product> MasterproductList = orderDao.getProductListByOrderId(masterOrder);
        List<Product> ChildproductList = orderDao.getProductListByOrderId(childOrder);


        //合并商品数量
        //遍历父商品
        ArrayList<Product> products = new ArrayList<>();
        for (Product productMaster : MasterproductList) {
            //拿到主订单的商品 用遍历有相同的我就添加
            boolean found = false;
            for (Product product : ChildproductList) {
                System.out.println(product.getId());
                System.out.println(productMaster.getId());
                if (Objects.equals(product.getId(), productMaster.getId())){
                    productMaster.setQuantity(product.getQuantity()+productMaster.getQuantity());
                    found = true;
                    break;
                }

            }
            if (!found) {
                products.add(productMaster);
            }
        }
        products.addAll(MasterproductList);

        // 添加子订单中不存在于父订单中的产品
        for (Product product : ChildproductList) {
            boolean found = false;
            for (Product productMaster : MasterproductList) {
                if (Objects.equals(product.getId(), productMaster.getId())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                products.add(product);
            }
        }

        //删除我的父与子订单号
        int order = orderDao.delOrder(childOrder);
        //更新我的父订单
        Order orderById = orderDao.getOrderById(masterOrder);
        Order orderById1 = orderDao.getOrderById(childOrder);
        double count=orderById1.getCost()+orderById.getCost();
        int order1 = orderDao.updateOrderByContail(count, orderById.getId());

        for (Product product : products) {
            boolean isFalg=false;
            for (Product Masteproduct : MasterproductList) {
                System.out.println(product.getId());
                System.out.println(Masteproduct.getId());
                if (Objects.equals(product.getId(), Masteproduct.getId())){
                    orderDao.updateOrderDetailByProductId(product.getQuantity(),product.getId(),orderById.getId());
                    isFalg=true;
                    break;
                }
            }
            if (!isFalg){
                orderDao.addOrderProductBytwo(orderById.getId(),product.getId(),product.getQuantity(),product.getQuantity()*product.getPrice());
            }
        }
//        删除子订单
        orderDao.delOrderProductByOrderId(orderById1.getId());

        return Message.success();
    }

}
