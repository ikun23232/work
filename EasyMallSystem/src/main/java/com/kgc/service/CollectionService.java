package com.kgc.service;

import com.kgc.entity.Message;

public interface CollectionService {
    public Message getConnectionById(int id);
    public Message delProductInCarById(int id);
    public Message UpdateProductInCarById(int id,int quantity);
    public Message addProductInCarById(int id,int quantity);
}
