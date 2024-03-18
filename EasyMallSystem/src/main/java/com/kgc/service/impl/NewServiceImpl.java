package com.kgc.service.impl;

import com.kgc.dao.NewsDao;
import com.kgc.entity.Message;
import com.kgc.entity.New;
import com.kgc.service.NewService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 13:55
 **/
@Service
public class NewServiceImpl implements NewService {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private NewsDao newsDao;

    @Override
    public Message getNewList() {
        logger.info("NewServiceImpl getNewList is start......");
        logger.info("NewServiceImpl newsDao getNewList is start......");
        List<New> newList = newsDao.getNewList();
        logger.debug("NewServiceImpl newsDao getNewList is start......newList" + newList);
        return Message.success(newList);
    }
}
