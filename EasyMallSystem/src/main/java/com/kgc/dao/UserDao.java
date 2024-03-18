package com.kgc.dao;

import com.kgc.entity.User;

import org.apache.ibatis.annotations.Param;

public interface UserDao {
    /**
     * 添加用户
     * @return
     */
    public int addUser(User user);

    /**
     * 通过用户名，密码查用户
     * @param loginName
     * @return
     */
    public User checkUserByNamePwd(@Param("loginName")String loginName, @Param("password")String password);
    /**
     * 通过名字查用户
     */
    public User checkUserByName(String loginName);

    /**
     * 通过名字修改密码
     */
    public int updataePasswordByName(@Param("loginName")String loginName, @Param("password")String password);

}
