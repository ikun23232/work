package com.kgc.controller;

import com.kgc.entity.Category;
import com.kgc.entity.Message;
import com.kgc.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        logger.info("CategoryController getCategoryList is start......categoryList" + categoryList);
        return Message.success(categoryList);
    }

    @RequestMapping("getCategorySecond")
    public Message getCategorySecond(int id) {
        logger.info("CategoryServiceImpl getCategorySecond is start....");
        Message message = categoryService.getCategorySecond(id);
        logger.info("CategoryServiceImpl getCategorySecond is start....message" + message);
        return message;
    }

    @RequestMapping("getCategoryThrid")
    public Message getCategoryThrid(int id) {
        logger.info("CategoryServiceImpl getCategorySecond is start....");
        Message message = categoryService.getCategoryThrid(id);
        logger.info("CategoryServiceImpl getCategorySecond is start....message" + message);
        return message;
    }

    @RequestMapping("getFristIdByThrid")
    public Message getFristIdByThrid(int id) {
        logger.info("CategoryServiceImpl getFristCategoryIdByThrid is start....");
        Message message = categoryService.getFristCategoryIdByThrid(id);
        logger.info("CategoryServiceImpl getFristCategoryIdByThrid is start....message" + message);
        return message;
    }

    @RequestMapping("getSecondIdByThrid")
    public Message getSecondIdByThrid(int id) {
        logger.info("CategoryServiceImpl getSecondCategoryIdByThrid is start....");
        Message message = categoryService.getSecondCategoryIdByThrid(id);
        logger.info("CategoryServiceImpl getSecondCategoryIdByThrid is start....message" + message);
        return message;
    }


}
