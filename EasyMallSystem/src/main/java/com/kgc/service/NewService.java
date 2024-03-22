package com.kgc.service;

import com.kgc.entity.Message;
import com.kgc.entity.News;
import com.kgc.entity.Page;

public interface NewService {
    public Message getNewList();


    public Message addNewList(News news);
    public Message getNewListByPage(String title, Page page);
    public Message delNew(int id);
    public Message updateNew(News news);
    public  Message getNewById(int id);
}
