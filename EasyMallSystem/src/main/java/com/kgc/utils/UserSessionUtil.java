package com.kgc.utils;

import com.alibaba.fastjson.JSON;
import com.kgc.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/**
 * @author: 欧洋宏
 * @create: 2024-03-25 16:18
 **/
@Component
public class UserSessionUtil implements ApplicationContextAware {
    @Autowired
    private static RedisUtil redisUtil;
    public static User getUser(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String loginName =(String) session.getAttribute("userInfo");

        String valueByKey = redisUtil.getValueByKey(loginName);
        User user = JSON.parseObject(valueByKey, User.class);
        return user;
    }

    public static int getUserId(){
        User user = getUser();
        return user.getId();
    }
    public static String getLoginName(){
        User user = getUser();
        return user.getLoginName();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisUtil = applicationContext.getBean(RedisUtil.class);
    }
}
