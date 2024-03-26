package com.kgc.service;

import com.kgc.entity.Message;
import com.kgc.entity.Product;

import java.util.List;

public interface CollectionService {
    public Message getConnectionById(int id);
    public Message delProductInCarById(int id);
    public Message UpdateProductInCarById(int id,int quantity);
    public Message addProductInCarById(int id,int quantity);

    public Message UpdateProductInCarById(List<Product> productList);

    public Message getProductById(int[] id,int count);

    Message delProductInCarByIds(String ids);

    Message getProductListByIdStr(String ids);

    public Message addtoCar(int userId,int productId,int quantity);

}
