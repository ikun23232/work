package com.kgc.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 22:30
 * Redis工具类
 **/
public class RedisUtil {
    @Autowired
    private static StringRedisTemplate stringRedisTemplate;

    public static void setKey(String key, String value, int minutes) {
        stringRedisTemplate.opsForValue().set(key, value, minutes, TimeUnit.MINUTES);
    }

    public static String getValueByKey(String key) {
        String value = stringRedisTemplate.opsForValue().get(key);
        return value;
    }

    public static boolean removeKey(String key) {
        Boolean deleteFalg = stringRedisTemplate.delete(key);
        return deleteFalg;
    }

}
