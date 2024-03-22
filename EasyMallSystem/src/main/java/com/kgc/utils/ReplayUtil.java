package com.kgc.utils;

import com.kgc.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 22:50
 **/
@Component
public class ReplayUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private Integer maxRandom=100; // 最大储存的随机数

    private Integer minRandom=20; // 最小储存的随机数

    private final List<String> randomList = new ArrayList<>(); // 待使用的随机数

    private final Map<String, String> useRandomMap = new HashMap<>(); // 使用的随机数

    /**
     * 将随机数加载至maxRandom的数量
     */
    public void createUUIDToRedis() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        for (int i = 0; i < maxRandom - randomList.size(); i++) {
            String random = UUID.randomUUID().toString();
            randomList.add(random);
            operations.set(random, random);
        }
    }

    /**
     * 创建单个随机数
     */
    public void createOneUUIDToRedis() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String random = UUID.randomUUID().toString();
        operations.set(random, random);
        randomList.add(random);
    }

    /**
     * 检查随机数
     *
     * @param random 需要检查的随机数
     * @return 返回随机数
     */
    public String checkRandom(String random) {
        String redisRandom = stringRedisTemplate.opsForValue().get(random);
        if (randomList.size() <= minRandom) {
            createUUIDToRedis();
        }
        return redisRandom;
    }

    /**
     * 获取随机数
     *
     * @return 返回随机数
     */
    public String getRandom() {
        int index = randomList.size() - 1;
        String random = randomList.get(index);
        useRandomMap.put(random, String.valueOf(new Date(new Date().getTime() + 60 * 60 * 1000)));
        randomList.remove(index);
        return random;
    }

    /**
     * 删除随机数
     *
     * @param random 需要删除的随机数
     */
    public void removeRandom(String random) {
        stringRedisTemplate.delete(random);
        useRandomMap.remove(random);
        createOneUUIDToRedis();
    }

    /**
     * 编码图片地址
     */
    public Product encodingFilePath(Product product){
        String picPath = null;
        try {
            picPath = URLEncoder.encode(product.getFilePath(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        product.setFilePath(picPath);
        return product;
    }
}
