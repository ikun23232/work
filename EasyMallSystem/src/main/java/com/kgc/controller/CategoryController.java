package com.kgc.controller;

import com.alibaba.fastjson.JSON;
import com.kgc.entity.Category;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
    public Message getCategorySecond(String id) {
        logger.info("CategoryServiceImpl getCategorySecond is start....");
        int ids= Integer.parseInt(id);
        Message message = categoryService.getCategorySecond(ids);
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

    @RequestMapping("getCategoryListByALL")
    public Message getCategoryListByALL(@RequestBody Map map) {
        logger.info("CategoryServiceImpl getCategorySecond is start....");
        Page page = JSON.parseObject(JSON.toJSONString(map.get("page")), Page.class);
        String categoryName = JSON.parseObject(JSON.toJSONString(map.get("categoryName")), String.class);
        String name = categoryName;
        Message message = categoryService.getCategoryListByALL(name, page);
        logger.info("CategoryServiceImpl getCategorySecond is start....message" + message);
        return message;
    }
    @RequestMapping("getCategoryListByOne")
    public Message getCategoryListByALL(String ids) {
        String replace = ids.replace("[", "");
        String[] split = replace.split(",");
        int i = Integer.parseInt(split[split.length - 1]);
        logger.info("CategoryServiceImpl getCategorySecond is start....");
        Message message = categoryService.getCategoryByid(i);
        logger.info("CategoryServiceImpl getCategorySecond is start....message" + message);
        return message;
    }

    @RequestMapping("getCategoryListall")
    public Message getCategoryListall() {
        logger.info("CategoryServiceImpl getCategorySecond is start....");
        Message message = categoryService.getCategoryListall();
        logger.info("CategoryServiceImpl getCategorySecond is start....message" + message);
        return message;
    }
    @RequestMapping("CheckCategoryName")
    public Message CheckCategoryName(String categoryName) {
        Message message = categoryService.CheckCategoryName(categoryName);
        return message;
    }

    @RequestMapping("CheckCategoryProductByType")
    public Message CheckCategoryProductByType(int type) {
        Message message = categoryService.CheckCategoryProductByType(type);
        return message;
    }

    @RequestMapping("delCategoryById")
    public Message delCategoryById(int id) {
        Message message = categoryService.delCategoryById(id);
        return message;
    }

    @RequestMapping("updateCategoryById")
    public Message updateCategoryById(@RequestBody Map map) {
        Category category = JSON.parseObject(JSON.toJSONString(map.get("category")), Category.class);

        Message message = categoryService.updateCategoryById(category);
        return message;
    }

    @RequestMapping("addCategoryById")
    public Message addCategoryById(@RequestBody Map map) {
        Category category = JSON.parseObject(JSON.toJSONString(map.get("category")), Category.class);

        Message message = categoryService.addCategoryById(category);
        return message;
    }
    @RequestMapping("getCategoryByid")
    public Message checkCategoryId(int id) {
        Message message = categoryService.getCategoryByid(id);
        return message;
    }




}
