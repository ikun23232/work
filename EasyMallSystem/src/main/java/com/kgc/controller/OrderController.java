package com.kgc.controller;

import com.alibaba.fastjson.JSON;
import com.kgc.entity.Address;
import com.kgc.entity.Message;
import com.kgc.entity.Order;
import com.kgc.entity.Product;
import com.kgc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: 欧洋宏
 * @create: 2024-03-20 10:22
 * 订单
 **/
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/getOrderProductByUserId")
    public Message getOrderProductByUserId() {
        //写死
        int id = 22;
        Message orderProductByUserId = orderService.getOrderProductByUserId(id);
        return orderProductByUserId;
    }

    @RequestMapping("/addOrder")
    public Message addOrder(@RequestBody Map map) {
        Address address = JSON.parseObject(JSON.toJSONString(map.get("address")), Address.class);
        String ids = JSON.parseObject(JSON.toJSONString(map.get("ids")), String.class);
        List productList = JSON.parseObject(JSON.toJSONString(map.get("productList")), List.class);
        String count = JSON.parseObject(JSON.toJSONString(map.get("cost")), String.class);
        List<Product> listProduct = new ArrayList<>();
        for (Object o : productList) {
            Product product = JSON.parseObject(JSON.toJSONString(o), Product.class);
            listProduct.add(product);
        }
        String[] split = ids.split(",");
        int[] idS=new int[split.length];
        for (int i = 0; i < split.length; i++) {
            idS[i]= Integer.parseInt(split[i]);
        }

        Message orderProductByUserId = orderService.addOrder(listProduct,address, Double.parseDouble(count),idS);
        return orderProductByUserId;
    }
}
