package com.kgc.dao;

import com.kgc.entity.Message;
import com.kgc.entity.User;

public interface UserDao {

    //通过loginName找用户
    public User searchUserById(String loginName);

}
