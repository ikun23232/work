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
}
