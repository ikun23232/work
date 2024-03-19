package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 欧洋宏
 * @create: 2024-03-19 08:46
 **/
@RestController
public class BrandController {
    @Autowired
    private BrandService brandService;

    @RequestMapping("getBrandList")
    public Message getBrandList() {
        Message brandList = brandService.getBrandList();
        return brandList;
    }
}
