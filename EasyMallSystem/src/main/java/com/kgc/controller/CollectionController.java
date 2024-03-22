package com.kgc.controller;

import com.alibaba.fastjson.JSON;
import com.kgc.entity.Message;
import com.kgc.entity.Product;
import com.kgc.service.CollectionService;
import org.apache.log4j.Logger;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: 欧洋宏
 * @create: 2024-03-19 15:05
 **/
@RestController
public class CollectionController {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private CollectionService collectionService;

    @RequestMapping("/getConnectionById")
    public Message getConnectionById() {
        //从redis用户信息拿id
        int id = 22;
        Message message = collectionService.getConnectionById(id);
        return message;
    }

    @RequestMapping("/addProductInCarById")
    public Message addProductInCarById(int id, int quantity) {

        Message message = collectionService.addProductInCarById(id, quantity);
        return message;
    }

    @RequestMapping("/updateProductInCarById")
    public Message getConnectionById(int id, int quantity) {
        Message message = collectionService.UpdateProductInCarById(id, quantity);
        return message;
    }

    @RequestMapping("/delProductInCarById")
    public Message getConnectionById(int id) {
        //从redis用户信息拿id
        Message message = collectionService.delProductInCarById(id);
        return message;
    }
    @RequestMapping("/delProductInCarByIds")
    public Message delProductInCarByIds(@RequestParam("ids")String ids) {


        //从redis用户信息拿id
        Message message = collectionService.delProductInCarByIds(ids);
        return message;
    }


    @RequestMapping("/updateProductInCarByList")
    public Message updateProductInCarByList(@RequestBody Map map) {
        List list = JSON.parseObject(JSON.toJSONString(map.get("productList")), List.class);
        List<Product> listProduct = new ArrayList<>();
        for (Object o : list) {
            Product product = JSON.parseObject(JSON.toJSONString(o), Product.class);
            listProduct.add(product);
        }
        Message message = collectionService.UpdateProductInCarById(listProduct);
        return message;
    }

    /**
     * 获取指定商品和套餐数
     */
    @RequestMapping("/getProductListByIdStr")
    public Message getProductListByIdStr(String idStr,int count) {
        String[] split = idStr.split(",");
        int[] ids=new int[split.length];
        for (int i = 0; i < split.length; i++) {
            ids[i]= Integer.parseInt(split[i]);
        }
        Message productById = collectionService.getProductById(ids, count);
        return productById;
    }

    /**
     * 获取指定商品进入订单页面
     */
    @RequestMapping("/getProductListByIdStrE")
    public Message getProductListByIdStr(@RequestParam("ids")String ids) {

        Message productById = collectionService.getProductListByIdStr(ids);
        return productById;
    }



}
