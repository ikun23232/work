package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.service.CollectionService;
import org.apache.log4j.Logger;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
