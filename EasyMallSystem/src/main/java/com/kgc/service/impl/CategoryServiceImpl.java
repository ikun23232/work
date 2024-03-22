package com.kgc.service.impl;

import com.kgc.dao.CategoryDao;
import com.kgc.entity.Category;
import com.kgc.entity.Message;
import com.kgc.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 14:58
 **/
@Service
public class CategoryServiceImpl implements CategoryService {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public Message getCategoryList() {
        logger.info("CategoryServiceImpl getCategoryList is start....");
        List<Category> categoryList = categoryDao.getCategoryList();
        logger.info("CategoryServiceImpl getCategoryList is start....categoryList"+categoryList);

        return Message.success(categoryList);
    }

    @Override
    public Message getCategorySecond(int id) {
        logger.info("CategoryServiceImpl getCategorySecond is start....");
        List<Category> categoryList = categoryDao.getCategorySecond(id);
        logger.info("CategoryServiceImpl getCategorySecond is start....categoryList" + categoryList);

        return Message.success(categoryList);
    }

    @Override
    public Message getCategoryThrid(int id) {
        logger.info("CategoryServiceImpl getCategorySecond is start....");
        List<Category> categoryList = categoryDao.getCategoryThrid(id);
        logger.info("CategoryServiceImpl getCategorySecond is start....categoryList" + categoryList);
        return Message.success(categoryList);
    }

    @Override
    public List<Category> getThreeCategoryList(String categoryName) {
        logger.info("CategoryServiceImpl getCategoryList is start....");
        List<Category> categoryList = categoryDao.getThreeCategoryByCategoryName(categoryName);
        logger.info("CategoryServiceImpl getThreeCategoryByCategoryName is start....categoryList"+categoryList);
        return categoryList;
    }

    @Override
    public Message getFristCategoryIdByThrid(int id) {
        logger.info("CategoryServiceImpl getFristCategoryIdByThrid is start....");
        Category category = categoryDao.getFristCategoryIdByThrid(id);
        return Message.success(category.getParentID());
    }

    @Override
    public Message getSecondCategoryIdByThrid(int id) {
        logger.info("CategoryServiceImpl getSecondCategoryIdByThrid is start....");
        Category category = categoryDao.getSecondCategoryIdByThrid(id);
        return Message.success(category.getParentID());
    }
}
