package com.kgc.service;

import com.kgc.entity.Message;

public interface UserService {

    //用户登录检查密码是否正确
    public Message loginTo(String loginName,String password);
}
