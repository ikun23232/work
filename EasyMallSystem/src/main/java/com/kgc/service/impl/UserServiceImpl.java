package com.kgc.service.impl;

import com.kgc.dao.UserDao;
import com.kgc.entity.Message;
import com.kgc.entity.User;
import com.kgc.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: 欧洋宏
 * @create: 2024-03-17 19:22
 **/
@Service
public class UserServiceImpl implements UserService {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private UserDao userDao;


    @Override
    public Message addUser(User user) {
        logger.info("UserServiceImpl addUser is start.......user " + user);
        logger.info("UserServiceImpl userDao addUser is start.......user " + user);
        int updateRow = userDao.addUser(user);
        logger.debug("UserServiceImpl userDao addUser is start.......user " + user + "updateRow" + updateRow);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("添加失败");
    }

    @Override
    public Message checkUserByName(String loginName) {
        logger.info("UserServiceImpl checkUserByName is start.......loginName " + loginName);
        logger.info("UserServiceImpl userDao checkUserByName is start.......loginName " + loginName);
        User user = userDao.checkUserByName(loginName);
        logger.debug("UserServiceImpl userDao checkUserByName is start.......user " + user);
        if (user!=null&&!user.getUserName().equals("")) {
            return Message.error("用户已存在");
        }
        return Message.success("可以注册");
    }

    @Override
    public Message checkUserByMobile(String mobile) {
        logger.info("UserServiceImpl checkUserByName is start.......mobile " + mobile);
        logger.info("UserServiceImpl userDao checkUserByName is start.......mobile " + mobile);
        User user = userDao.checkUserByMobile(mobile);
        logger.debug("UserServiceImpl userDao checkUserByName is start.......user " + user);
        if (user!=null&&!user.getUserName().equals("")) {
            return Message.success(user);
        }
        return Message.error("手机已被注册");
    }

    @Override
    public Message checkUserByEmail(String email) {
        logger.info("UserServiceImpl checkUserByName is start.......email " + email);
        logger.info("UserServiceImpl userDao checkUserByName is start.......email " + email);
        User user = userDao.checkUserByEmail(email);
        logger.debug("UserServiceImpl userDao checkUserByName is start.......user " + user);
        if (user!=null&&!user.getUserName().equals("")) {
            return Message.success(user);
        }
        return Message.error("邮箱已被注册");
    }

}
