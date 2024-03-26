package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.NewsDao;
import com.kgc.entity.Message;
import com.kgc.entity.News;
import com.kgc.entity.Page;
import com.kgc.service.NewService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 13:55
 **/
@Service
@Transactional
public class NewServiceImpl implements NewService {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private NewsDao newsDao;

    @Override
    public Message getNewList() {
        logger.info("NewServiceImpl getNewList is start......");
        logger.info("NewServiceImpl newsDao getNewList is start......");
        List<News> newList = newsDao.getNewList();
        logger.debug("NewServiceImpl newsDao getNewList is start......newList" + newList);
        return Message.success(newList);
    }

    @Override
    public Message addNewList(News news) {
        int rows = newsDao.addNewList(news);
        if (rows>0){
            return Message.success("添加成功");
        }
        return Message.error("添加失败");
    }

    @Override
    public Message getNewListByPage(String title, Page page) {
        PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize());
        String titleTemp=title;
        List<News> newListByPage = newsDao.getNewListByPage(titleTemp);
        PageInfo<News> pageInfo = new PageInfo<>(newListByPage);

            return Message.success(pageInfo);


    }

    @Override
    public Message delNew(int id) {
        int rows = newsDao.delNew(id);
        if (rows>0){
            return Message.success("删除成功");
        }
        return Message.error("删除失败");
    }

    @Override
    public Message updateNew(News news) {
        int rows = newsDao.updateNew(news);
        if (rows>0){
            return Message.success("修改成功");
        }
        return Message.error("修改失败");
    }

    @Override
    public Message getNewById(int id) {
        News newById = newsDao.getNewById(id);
        return Message.success(newById);
    }


}
