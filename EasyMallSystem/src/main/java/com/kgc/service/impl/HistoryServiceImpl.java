package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.HistoryDao;
import com.kgc.entity.History;
import com.kgc.entity.Message;
import com.kgc.entity.Product;
import com.kgc.service.HistoryService;
import com.kgc.utils.ReplayUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/20/ 16:05
 * @Description
 */
@Service
@Transactional
public class HistoryServiceImpl implements HistoryService {

    private Logger logger = Logger.getLogger(HistoryServiceImpl.class);
    @Autowired
    private HistoryDao historyDao;
    @Autowired
    private ReplayUtil replayUtil;
    @Override
    public Message getHistoryList(int id) {
        logger.info("HistoryServiceImpl getHistoryList is start....");
        List<Product> productList = historyDao.getHistoryListById(id);
//        PageHelper.startPage(currentPageNo,pageSize);
//        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        List<Product> products = new ArrayList<>();
        for(Product product:productList){
            replayUtil.encodingFilePath(product);
            products.add(product);
        }
//        pageInfo.setList(products);
        return Message.success(products);
    }

    @Override
    public Message addHistory(int userId, int productId) {
        logger.info("HistoryServiceImpl addHistory is start....");
        History history= historyDao.getHistoryByPidUserId(productId, userId);
        if(history!=null){
            historyDao.delHistoryById(history.getId());
        }
        int i = historyDao.addHistoryById(productId, userId);
        if(i>0){
            return Message.success();
        }else {
            return Message.error();
        }
    }
}
