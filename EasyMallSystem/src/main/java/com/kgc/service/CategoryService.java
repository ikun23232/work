package com.kgc.service;

import com.kgc.entity.Category;
import com.kgc.entity.Message;

import java.util.List;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 14:58
 **/
public interface CategoryService {
    public Message getCategoryList();
    public Message getCategorySecond(int id);
    public Message getCategoryThrid(int id);

    /**
     * 拿到某一级类下的三级类的集合
     */
    public  List<Category> getThreeCategoryList(String categoryName);

    /**
     * 拿一级类id 通过三级id
     */
    public Message getFristCategoryIdByThrid(int id);

    /**
     * 拿二级类id 通过三级id
     */
    public Message getSecondCategoryIdByThrid(int id);
}
