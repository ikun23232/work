package com.kgc.dao;

import com.kgc.entity.User;

public interface UserDao {
    /**
     * 添加用户
     *
     * @return
     */
    public int addUser(User user);

    /**
     * 通过名字查用户
     */
    public User checkUserByName(String loginName);

    public User checkUserByMobile(String mobile);

    public User checkUserByEmail(String email);

}
