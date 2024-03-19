package com.kgc.service.impl;

import com.kgc.dao.CollectionDao;
import com.kgc.entity.Collections;
import com.kgc.entity.Message;
import com.kgc.entity.Product;
import com.kgc.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.List;

/**
 * @author: 欧洋宏
 * @create: 2024-03-19 15:07
 **/
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionDao collectionDao;

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
        if (updateRow>0){
            return Message.success();
        }
        return Message.error("删除失败");
    }

    @Override
    public Message UpdateProductInCarById(int id, int quantity) {
        int updateRow = collectionDao.UpdateProductInCarById(id,quantity);
        if (updateRow>0){
            return Message.success();
        }
        return Message.error("修改失败");
    }

    @Override
    public Message addProductInCarById(int id, int quantity) {
        int updateRow = collectionDao.addProductInCarById(id,quantity);
        if (updateRow>0){
            return Message.success();
        }
        return Message.error("修改失败");
    }


}
