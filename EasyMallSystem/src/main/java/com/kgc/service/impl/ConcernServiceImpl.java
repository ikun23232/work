package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.ConcernDao;
import com.kgc.entity.Concern;
import com.kgc.entity.Message;
import com.kgc.entity.Product;
import com.kgc.entity.User;
import com.kgc.service.ConcernService;
import com.kgc.service.ProductService;
import com.kgc.utils.ElsearchUtil;
import com.kgc.utils.ReplayUtil;
import com.kgc.utils.UserSessionUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/24/ 12:16
 * @Description
 */
@Service
@Transactional
public class ConcernServiceImpl  implements ConcernService {
    private Logger logger = Logger.getLogger(ConcernServiceImpl.class);

    @Autowired
    private ConcernDao concernDao;

    @Autowired
    private ProductService productService;
    @Autowired
    private ReplayUtil replayUtil;
    @Override
    public Message getConcernPageList(int currentPageNo, int pageSize) {
        //此处拿到登录的用户的权限
        int userId = UserSessionUtil.getUserId();
        List<Product> products = new ArrayList<>();
        PageHelper.startPage(currentPageNo, pageSize);
        List<Product> productList = concernDao.getConcernPageList(userId);
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        for (Product product : productList) {
            replayUtil.encodingFilePath(product);
            products.add(product);
        }
        pageInfo.setList(products);
        return Message.success(pageInfo);
    }

    @Override
    public Message delConcern(int id) {
        int i = concernDao.delConcern(id);
        if(i>0){
            return Message.success();
        }else {
            return Message.error();
        }
    }

    @Override
    public Message addCercon(int productId) {
        //拿到登录的userid
        int userId = UserSessionUtil.getUserId();
        Concern concern = new Concern();
        concern.setUserId(userId);
        concern.setProductId(productId);
        int i = concernDao.addConcern(concern);
        if(i>0){
            return Message.success();
        }else {
            return Message.error();
        }
    }
}
