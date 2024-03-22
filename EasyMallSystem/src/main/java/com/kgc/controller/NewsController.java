package com.kgc.controller;

import com.alibaba.fastjson.JSON;
import com.kgc.entity.Message;
import com.kgc.entity.News;
import com.kgc.entity.Page;
import com.kgc.service.NewService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
     *
     * @return
     */
    @RequestMapping("/getNewsList")
    public Message getNewsList() {
        logger.info("NewsController getNewsList is start...");
        Message newList = newService.getNewList();
        logger.info("NewsController getNewsList is start...newList" + newList);
        return newList;
    }

    @RequestMapping("/addNewList")
    public Message addNewList(@RequestBody Map map) {

        News news = JSON.parseObject(JSON.toJSONString(map.get("News")), News.class);
        Message message = newService.addNewList(news);
        return message;
    }

    @RequestMapping("/getNewListByPage")
    public Message getNewListByPage(@RequestBody Map map) {
        Page page = JSON.parseObject(JSON.toJSONString(map.get("page")), Page.class);
        String titleTemp = JSON.parseObject(JSON.toJSONString(map.get("title")), String.class);
        String title=titleTemp;
        Message message = newService.getNewListByPage(title, page);
        return message;
    }

    @RequestMapping("/delNew")
    public Message delNew(int id) {
        Message message = newService.delNew(id);
        return message;
    }

    @RequestMapping("/updateNew")
    public Message updateNew(@RequestBody Map map) {
        News news = JSON.parseObject(JSON.toJSONString(map.get("News")), News.class);
        Message message = newService.updateNew(news);
        return message;
    }

    @RequestMapping("/getNewById")
    public Message getNewById(int id) {
        Message message = newService.getNewById(id);
        return message;
    }
}
