package com.kgc.service.impl;

import com.kgc.entity.Message;
import com.kgc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author: 欧洋宏
 * @create: 2024-03-17 19:22
 **/
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = Logger.getLogger(getClass());

    @Override
    public Message loginTo(String loginName, String password) {
        logger.info("UserServiceImpl loginTo is start ===============");

        return null;
    }
}
