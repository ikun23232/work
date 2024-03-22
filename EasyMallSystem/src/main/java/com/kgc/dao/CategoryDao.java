package com.kgc.dao;

import com.kgc.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {
    public List<Category> getCategoryList();
    public List<Category> getCategorySecond(int id);
    public List<Category> getCategoryThrid(int id);


    /**
     * 通过一级类名查找三级类名
     */
    public List<Category> getThreeCategoryByCategoryName(String categoryName);

    public List<Category> getCategoryListByALL(@Param("categoryName") String categoryName);

    /**
     * 直接查全部不带分页版和模糊查询版
     * @param
     * @return
     */
    public List<Category> getCategoryListall();

    /**
     * 检查分类名不能有冲突
     * @param categoryName
     * @return
     */
    public  Category CheckCategoryName(String categoryName);

    /**
     * 检查指定分类下是否有其他产品
     * @param id
     * @return
     */
    public  int CheckCategoryProductByid(int id);
    /**
     * 检查指定分类下是否还有其他分类
     * @param id
     * @return
     */
    public  List<Category> CheckChidrenCategoryByid(int id);
    /**
     * 返回指定类型下的分类集合
     * @param type
     * @return
     */
    public  List<Category> CheckCategoryProductByType(int type);


    /**
     * 删除指定id下的分类
     * @param id
     * @return
     */
    public  int delCategoryById(int id);
    /**
     * 修改指定id下的分类
     * @param category
     * @return
     */
    public  int updateCategoryById(Category category);

    /**
     * 增加分类
     * @param category
     * @return
     */
    public int addCategoryById(Category category);

    public int getTypeById(int id);


    public Category getCategoryById(int id);
    public Category getCategoryList(int type);


}
