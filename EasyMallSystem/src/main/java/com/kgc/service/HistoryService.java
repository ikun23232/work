package com.kgc.service;

import com.kgc.entity.Message;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/20/ 16:04
 * @Description
 */
public interface HistoryService {

    /**
     * 得到最新的五条浏览记录
     */
    public Message getHistoryList(int id);

    /**
     * 点击商品添加浏览记录
     */
    public Message addHistory(int userId,int productId);
}
