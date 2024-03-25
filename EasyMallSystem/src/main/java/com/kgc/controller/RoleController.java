package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.service.BrandService;
import com.kgc.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/23/ 11:31
 * @Description
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("getRoleList")
    public Message getBrandList() {
        Message roleList = roleService.getRoleList();
        return roleList;
    }

    @RequestMapping("getMyroleId")
    public Message getMyroleId() {
        Message roleList = roleService.getMyroleId();
        return roleList;
    }

}
