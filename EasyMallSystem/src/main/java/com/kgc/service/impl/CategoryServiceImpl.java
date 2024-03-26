package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.CategoryDao;
import com.kgc.entity.Category;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.User;
import com.kgc.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 14:58
 **/
@Service
@Transactional
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
    public Message getCategoryListByALL(String name,int type, Page page) {

        PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize());
        List<Category> categoryList = categoryDao.getCategoryListByALL(name,type);
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        return Message.success(pageInfo);
    }

    @Override
    public Message CheckCategoryName(String categoryName) {
        Category category = categoryDao.CheckCategoryName(categoryName);
        if (category!=null){
            return Message.error("该分类名已经存在");
        }
        return Message.success();
    }

    @Override
    public Message CheckCategoryProductByid(int id) {
        int row = categoryDao.CheckCategoryProductByid(id);
        if (row>0){
            return Message.error("该分类名下存在商品");
        }
        return Message.success();
    }

    @Override
    public Message CheckCategoryProductByType(int type) {
        List<Category> categories = categoryDao.CheckCategoryProductByType(type);
        return Message.success(categories);

    }

    @Override
    public Message delCategoryById(int id) {
        List<Category> categories = categoryDao.CheckChidrenCategoryByid(id);
        if (categories.size()>0){
            return Message.error("该分类下还有子分类添加失败!");
        }
        //判断该分类里面有无商品
        int i = categoryDao.CheckCategoryProductByid(id);
        if (i>0){
            return Message.error("该分类下还有商品添加失败!");
        }
        int update = categoryDao.delCategoryById(id);
        if (update>0){
            return Message.success();
        }
        return Message.error("删除失败");
    }

    @Override
    public Message updateCategoryById(Category category) {
        //重名校验
        Category category1 = categoryDao.CheckCategoryName(category.getCategoryName());
        if (category1!=null&&!category1.getCategoryName().equals(category.getCategoryName())){
            return Message.error("有重复的分类名,更新失败");
        }
        int update = categoryDao.updateCategoryById(category);

        if (update>0){
            return Message.success();
        }
        return Message.error("更新失败");
    }

    @Override
    public Message addCategoryById(Category category) {

        if (category.getParentId()==0){
    category.setType(1);
    }else {
    int type= categoryDao.getTypeById(category.getParentId());
    category.setType(type+1);
}

        int update = categoryDao.addCategoryById(category);
        if (update>0){
            return Message.success();
        }
        return Message.error("添加失败");
    }

    @Override
    public Message getCategoryListall() {
        List<Category> listall = categoryDao.getCategoryListall();
        return Message.success(listall);
    }

    @Override
    public Message getCategoryByid(int id) {
        Category categoryById = categoryDao.getCategoryById(id);
        return Message.success(categoryById);
    }

    @Override
    public Message getFristCategoryIdByThrid(int id) {
        logger.info("CategoryServiceImpl getFristCategoryIdByThrid is start....");
        Category category = categoryDao.getFristCategoryIdByThrid(id);
        return Message.success(category.getParentId());
    }

    @Override
    public Message getSecondCategoryIdByThrid(int id) {
        logger.info("CategoryServiceImpl getSecondCategoryIdByThrid is start....");
        Category category = categoryDao.getSecondCategoryIdByThrid(id);
        return Message.success(category.getParentId());
    }

    @Override
    public Message CheckUpdateCategoryName(String categoryName, int id) {
        //根据id返回CategoryId对象
        Category categoryById = categoryDao.getCategoryById(id);
        if (categoryName.equals(categoryById.getName())){
            return Message.success();
        }
        Category category = categoryDao.CheckCategoryName(categoryName);
        if (category==null&&category.getCategoryName().equals("")){
            return Message.success();
        }
        return Message.error();
    }

//    @Override
//    public Message getCategoryListById(int id) {
//        int typeById = categoryDao.getTypeById(id);
//        ArrayList<Category> categories = new ArrayList<>();
//        if(typeById==1){
//            List<Category> threeCategoryBycategoryId = categoryDao.getThreeCategoryBycategoryId(id);
//            categoryDao.getThreeCategoryBycategoryId()
//        }
//        return null;
//    }
}
