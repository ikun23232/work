package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.service.NewService;
import com.kgc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 13:45
 **/
@RestController
public class NewsController {
    private Logger logger = Logger.getLogger(getClass());

    @Autowired
    private NewService newService;

    /**
     * 访问最近的新闻列表
     * @return
     */
    @RequestMapping("/getNewsList")
    public Message getNewsList() {
        logger.info("NewsController getNewsList is start...");
        Message newList = newService.getNewList();
        logger.info("NewsController getNewsList is start...newList" + newList);
        return newList;
    }
}
