package com.kgc.service;

import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.Product;

import com.kgc.entity.Product;

import java.util.List;

public interface ProductService {

    /**
     *通过一级类找到下面的所有商品
     */
    public Message searchProductByCategoryName(String categoryName);

    /**
     * 查找四条热门产品（销量）
     * @return
     */
    public Message searchHotProduct();

    /**
     * 通过id找产品
     * @return
     */
    public Message getProductById(int id);


    public Message getProductListAll();

    public Message addProductListByALL();
    public Message getProductListByPage(Page page, Product product,int minPrice,int MaxPrice);
}
