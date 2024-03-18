package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 15:12
 **/
@RestController
public class CategoryController {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("getCategoryList")
    public Message getCategoryList() {
        logger.info("CategoryController getCategoryList is start......");
        Message categoryList = categoryService.getCategoryList();
        logger.info("CategoryController getCategoryList is start......categoryList"+categoryList);
        return Message.success(categoryList);
    }

}
