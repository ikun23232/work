package com.kgc.dao;

import com.kgc.entity.Category;

import java.util.List;

public interface CategoryDao {
    public List<Category> getCategoryList();
    public List<Category> getCategorySecond(int id);
    public List<Category> getCategoryThrid(int id);


    /**
     * 通过一级类名查找三级类名
     */
    public List<Category> getThreeCategoryByCategoryName(String categoryName);

    /**
     * 通过三级类Id找二级类Id
     */
    public Category getSecondCategoryIdByThrid(int id);

    /**
     * 通过三级类Id找一级类id
     */
    public Category getFristCategoryIdByThrid(int id);
}
