package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 欧洋宏
 * @create: 2024-03-17 19:17
 **/
@RestController
public class UserController {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private UserService userService;

    @RequestMapping("test")
    public String test() {
        return "test";
    }

    @RequestMapping("/aaa")

    public Message aaa(String loginName){
        Message message = userService.checkUserByName(loginName);
        return message;
    }

    @RequestMapping("/loginto")
    public Message loginTo(String loginName,String password){
        logger.info("UserController loginTo is start......loginName:"+loginName+"password:"+password);
        Message message = userService.checkUserByNamePwd(loginName,password);
        return message;
    }
}
