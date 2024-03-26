package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.service.ConcernService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/24/ 12:21
 * @Description
 */
@RestController
public class ConcernController {

    private Logger logger  = Logger.getLogger(ConcernController.class);
    @Autowired
    private ConcernService concernService;

    @RequestMapping("/getConcernPageList")
    public Message getConcernPageList(int currentPageNo,int pageSize){
        Message message = concernService.getConcernPageList(currentPageNo, pageSize);
        return message;
    }

    @RequestMapping("/delConcern")
    public Message delConcern(int id){
        Message message = concernService.delConcern(id);
        return message;
    }

    @RequestMapping("/addCercon")
    public Message addCercon(int productId){
        Message message = concernService.addCercon(productId);
        return message;
    }



}
