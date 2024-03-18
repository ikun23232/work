package com.kgc.service.impl;

import com.kgc.dao.ProductDao;
import com.kgc.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: 欧洋宏
 * @create: 2024-03-18 15:50
 **/
@Service
public class ProductServiceImpl implements ProductService {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private ProductDao productDao;

}
