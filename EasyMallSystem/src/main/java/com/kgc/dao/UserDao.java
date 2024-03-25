package com.kgc.dao;

import com.kgc.entity.User;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    /**
     * 添加用户
     *
     * @return
     */
    public int addUser(User user);

    /**
     * 通过用户名，密码查用户
     *
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
    public int updataePasswordByName(@Param("loginName") String loginName, @Param("password") String password);

    public User checkUserByMobile(String mobile);

    public User checkUserByEmail(String email);

    /**
     * 分页查询所有的用户
     */
    public List<User> getUserPage(@Param("userName") String userName, @Param("roleId") int roleId, @Param("rid") int rid);

    /**
     * 删除用户
     */
    public int delUser(int id);

    /**
     * 通过id查找用户
     */
    public User getUserById(int id);

    /**
     * 修改用户
     */
    public int updateUser(User user);

}
