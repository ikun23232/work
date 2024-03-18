package com.kgc.dao;

import com.kgc.entity.Product;

import java.util.List;

public interface ProductDao {
    public List<Product> getProductList();


    /**
     * 通过三级类名查找产品
     */
    public List<Product> getProductBythreeCategoryName(String categoryName);

    /**
     * 通过productId找到file文件
     */
    public String getfileByproductId(int id);

    /**
     * 通过id找product
     */
    public Product getProductById(int id);
}
