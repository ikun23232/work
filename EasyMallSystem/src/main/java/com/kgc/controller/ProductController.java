package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.entity.Product;
import com.kgc.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 15:47
 **/
@RestController
public class ProductController {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private ProductService productService;

    @RequestMapping("/getProductWithFileList")
    public Message getProductWithFileList(String categoryName){
        logger.info("ProductController getProductList is start......categoryName:"+categoryName);
        List<Product> productList = productService.searchProductByCategoryName(categoryName);
        logger.info("ProductController getProductList is start......categoryName:"+categoryName+"result"+productList);
        return Message.success(productList);
    }
}
