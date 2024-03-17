package com.kgc.controller;

import com.kgc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

//    /**
//     * 添加用户
//     * @return
//     */
//    @RequestMapping("test")
//    public String test() {
//        return "test";
//    }
}
