package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.Product;
import com.kgc.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 15:47
 **/
@RestController
public class ProductController {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private ProductService productService;

    @RequestMapping("/getProductList")
    public Message getProductList() {
        logger.info("ProductController getProductList is start.........");
        Message productListAll = productService.getProductListAll();
        logger.info("ProductController getProductList is start.........Message" + productListAll);

        return productListAll;

    }

    @RequestMapping("/addProductList")
    public Message addProductList() {
        logger.info("ProductController getProductList is start.........");
        Message productListAll = productService.addProductListByALL();
        logger.info("ProductController getProductList is start.........Message" + productListAll);
        return productListAll;
    }

    @RequestMapping("/getProductByProduct")
    public Message getProductByProduct(int PageSize,int currentPageNo,String name) {
        logger.info("ProductController getProductList is start.........");
        Page page=new Page();
        page.setPageSize(PageSize);
        page.setCurrentPageNo(currentPageNo);
        Product product=new Product();
        product.setName(name);
        Message productListAll = productService.getProductListByPage(page, product);
        logger.info("ProductController getProductList is start.........Message" + productListAll);
        return productListAll;
    }


}
