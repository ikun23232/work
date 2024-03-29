package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.HistoryService;
import com.kgc.utils.UserSessionUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/20/ 16:15
 * @Description
 */
@RestController
public class HistoryController {

    private Logger logger = Logger.getLogger(HistoryController.class);

    @Autowired
    private HistoryService historyService;

    @RequestMapping("/getHistoryList")
    public Message getHistoryList(){
        logger.info("HistoryController getHistoryList is start...");
        //从redis拿id
        int userId = UserSessionUtil.getUserId();
//        page.setPageSize(5);
        Message message = historyService.getHistoryList(userId);
//        Object pageDTO = message.getData();
        return message;
    }

    @RequestMapping("/addHistory")
    public Message addHistory(int productId){
        logger.info("HistoryController getHistoryList is start...");
        //从redis拿id
        int userId = UserSessionUtil.getUserId();
        Message message = historyService.addHistory(userId,productId);
        return message;
    }
}
