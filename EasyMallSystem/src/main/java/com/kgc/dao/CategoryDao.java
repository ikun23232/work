package com.kgc.dao;

import com.kgc.entity.Category;

import java.util.List;

public interface CategoryDao {
    public List<Category> getCategoryList();
    public List<Category> getCategorySecond(int id);
    public List<Category> getCategoryThrid(int id);

}
