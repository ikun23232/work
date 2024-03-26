package com.kgc.service.impl;

import com.kgc.dao.BrandDao;
import com.kgc.entity.Brand;
import com.kgc.entity.Message;
import com.kgc.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 欧洋宏
 * @create: 2024-03-19 09:04
 **/
@Service
@Transactional
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandDao brandDao;

    @Override
    public Message getBrandList() {
        List<Brand> brandList = brandDao.getBrandList();
        return Message.success(brandList);
    }
}
