package com.kgc.dao;

import com.kgc.entity.Message;
import com.kgc.entity.User;

import com.kgc.entity.User;

public interface UserDao {
    /**
     * 添加用户
     * @return
     */
    public int addUser(User user);

    //通过loginName找用户
    public User searchUserById(String loginName);
    /**
     * 通过名字查用户
     */
    public User checkUserByName(String loginName);

}
