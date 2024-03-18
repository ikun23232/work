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
    public List<Product> searchProductByCategoryName(String categoryName);

    public Message getProductListAll();

    public Message addProductListByALL();
    public Message getProductListByPage(Page page, Product product);
}
