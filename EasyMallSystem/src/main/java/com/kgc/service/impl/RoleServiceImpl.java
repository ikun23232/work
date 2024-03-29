package com.kgc.service.impl;

import com.kgc.dao.RoleDao;
import com.kgc.entity.Message;
import com.kgc.entity.Role;
import com.kgc.entity.User;
import com.kgc.service.RoleService;
import com.kgc.utils.UserSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/23/ 11:30
 * @Description
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Override
    public Message getRoleList() {
        //此出拿到登录用户的rid
        //普通管理员只能看管理员和普通用户
        User user = UserSessionUtil.getUser();
        int id = 1;
        //如果登录用户是超级管理员那么就可以看所有的
        if(user.getRoleId() == 1){
            id = 0;
        }
        List<Role> roleList = roleDao.getRoleList(id);
        return Message.success(roleList);
    }

    @Override
    public Message getMyroleId() {
        //在此处得到登录用户的roleId
        User user = UserSessionUtil.getUser();
        return Message.success(user.getRoleId());
    }
}
