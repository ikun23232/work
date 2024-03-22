package com.kgc.dao;

import com.kgc.entity.Page;
import com.kgc.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

    /**
     * 查找所有商品
     * @return
     */
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

    /**
     * 通过销量查找product
     */
    public List<Product> getProductByOrder();

    /**
     * 通过pid查找相同第一级别的商品
     */
    public List<Product> getSameFristCategoryProductByPid(int pid);

    /**
     * 通过pid删除商品（逻辑）
     */
    public int delProductById(int id);

    /**
     * 查找分页所有商品通过pid、brandid
     * @return
     */
    public List<Product> getProductPageList(@Param("productName") String productName,@Param("brandId") int brandId);

    /**
     * 查找分页所有商品通过pid、brandid
     * @return
     */
    public int addProduct(Product product);


}
