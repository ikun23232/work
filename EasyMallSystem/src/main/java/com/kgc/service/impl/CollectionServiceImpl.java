package com.kgc.service.impl;

import com.kgc.dao.CollectionDao;
import com.kgc.dao.ProductDao;
import com.kgc.entity.Collections;
import com.kgc.entity.Message;
import com.kgc.entity.Product;
import com.kgc.service.CollectionService;
import com.kgc.utils.RedisUtil;
import com.kgc.utils.UserSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: 欧洋宏
 * @create: 2024-03-19 15:07
 **/
@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionDao collectionDao;

    @Autowired
    private ProductDao productDao;

    @Override
    public Message getConnectionById(int id) {
        List<Product> connection = collectionDao.getConnectionById(id);
        try {
            for (Product product : connection) {
                product.setFilePath(URLEncoder.encode(product.getFilePath(), "utf-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Message.success(connection);
    }

    @Override
    public Message delProductInCarById(int id) {
        int updateRow = collectionDao.delProductInCarById(id);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("删除失败");
    }

    @Override
    public Message UpdateProductInCarById(int id, int quantity) {
        int updateRow = collectionDao.UpdateProductInCarById(id, quantity);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("修改失败");
    }

    @Override
    public Message addProductInCarById(int id, int quantity) {
        int userId = UserSessionUtil.getUserId();
        int updateRow = collectionDao.addProductInCarById(id, quantity,userId);
        if (updateRow > 0) {
            return Message.success();
        }
        return Message.error("修改失败");
    }

    @Override
    public Message UpdateProductInCarById(List<Product> productList) {
        int counts = productList.size();
        int sum = 0;
        for (int i = 0; i < productList.size(); i++) {

            Product product = productList.get(i);
            int updateRow = collectionDao.UpdateProductInCarById(product.getId(), product.getQuantity());
            if (updateRow > 0) {
                sum++;
            }


        }

        if (sum != counts) {
            return Message.error("更新失败");
        }
        return Message.success();
    }

    @Override
    public Message getProductById(int[] id, int count) {
        ArrayList<Product> Products = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            Product product = productDao.getProductById(id[i]);
            try {
                product.setFilePath(URLEncoder.encode(product.getFilePath(),"utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            product.setQuantity(count);
            Products.add(product);
        }
        return Message.success(Products);
    }

    @Override
    public Message delProductInCarByIds(String ids) {
        String[] idStr = ids.split(",");
        for (int i = 0; i < idStr.length; i++) {
            collectionDao.delProductInCarById(Integer.parseInt(idStr[i]));
        }
        return Message.success();
    }

    @Override
    public Message getProductListByIdStr(String ids) {
        String[] idStr = ids.split(",");
        int[]idS=new int[idStr.length];

        for (int i = 0; i < idStr.length; i++) {
            idS[i]= Integer.parseInt(idStr[i]);
        }
        int userId = UserSessionUtil.getUserId();
        List<Product> productAndQuantityById = collectionDao.getProductAndQuantityById(idS,userId);
        if (productAndQuantityById==null&&productAndQuantityById.size()>0){
            return Message.error("无数据");
        }
        for (Product product : productAndQuantityById) {
            try {
                product.setFilePath(URLEncoder.encode(product.getFilePath(),"utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return Message.success(productAndQuantityById);
    }


}
