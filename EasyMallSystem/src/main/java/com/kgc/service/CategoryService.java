package com.kgc.service;

import com.kgc.entity.Category;
import com.kgc.entity.Message;
import com.kgc.entity.Page;

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
     *返回指定分类名的分页查询
     * @param name
     * @return
     */
    public Message getCategoryListByALL(String name, Page page);


    /**
     * 检查分类名不能有冲突
     * @param categoryName
     * @return
     */
    public  Message CheckCategoryName(String categoryName);

    /**
     * 检查指定分类下是否有其他产品
     * @param id
     * @return
     */
    public  Message CheckCategoryProductByid(int id);
    /**
     * 返回指定类型下的分类集合
     * @param type
     * @return
     */
    public  Message CheckCategoryProductByType(int type);


    /**
     * 删除指定id下的分类
     * @param id
     * @return
     */
    public  Message delCategoryById(int id);
    /**
     * 修改指定id下的分类
     * @param category
     * @return
     */
    public  Message updateCategoryById(Category category);
    /**
     * 增加分类
     * @param category
     * @return
     */
    public Message addCategoryById(Category category);

    public Message getCategoryListall();

    public Message getCategoryByid(int id);


    /**
     * 拿一级类id 通过三级id
     */
    public Message getFristCategoryIdByThrid(int id);

    /**
     * 拿二级类id 通过三级id
     */
    public Message getSecondCategoryIdByThrid(int id);
}
