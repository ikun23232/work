package com.kgc.dao;

import com.kgc.entity.Category;

import java.util.List;

public interface CategoryDao {
    public List<Category> getCategoryList();

    /**
     * 通过一级类名查找三级类名
     */
    public List<Category> getThreeCategoryByCategoryName(String categoryName);
}
