package com.kgc.utils;

import com.alibaba.fastjson.JSON;
import com.kgc.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import static com.kgc.constant.UserConstant.USER_SESSION;

/**
 * @author: 欧洋宏
 * @create: 2024-03-25 16:18
 **/
@Component
public class UserSessionUtil implements ApplicationContextAware {
    @Autowired
    private static RedisUtil redisUtil;
    public static User getUser(){
        String valueByKey = redisUtil.getValueByKey(USER_SESSION);
        User user = JSON.parseObject(valueByKey, User.class);
        return user;
    }

    public static int getUserId(){
        User user = getUser();
        return user.getId();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        redisUtil = applicationContext.getBean(RedisUtil.class);
    }
}
