package com.kgc.service;

import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.entity.Product;

public interface ProductService {
    public Message getProductListAll();

    public Message addProductListByALL();
    public Message getProductListByPage(Page page, Product product);
}
