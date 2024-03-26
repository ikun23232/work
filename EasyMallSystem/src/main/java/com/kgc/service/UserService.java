package com.kgc.service;

import com.kgc.entity.Message;
import com.kgc.entity.Product;
import com.kgc.entity.User;

public interface UserService {
    /**
     * 添加用户
     * @return
     */
    public Message addUser(User user);

    /**
     * 重名校验
     * @param loginName
     * @return
     */
    public Message checkUserByName(String loginName);

    /**
     * 修改时重名校验
     * @param loginName
     * @return
     */
    public Message checkUserByUpdateName(String loginName,int id);


    /**
     *修改密码界面检查用户名存在
     */
    public Message checkUserByLoginName(String loginName,String email);

    /**
     * 用户登录检查密码是否正确
     * @param loginName
     * @param password
     * @return
     */
    public Message checkUserByNamePwd(String loginName,String password);


    /**
     * 修改密码
     * @param loginName
     * @param password
     * @return
     */
    public Message updatePassword(String loginName,String password);



    /**
     * 校验手机号
     * @param mobile
     * @return
     */
    public Message checkUserByMobile(String mobile);

    /**
     * 校验手机号
     * @param mobile
     * @return
     */
    public Message checkUserByUpdateMobile(String mobile,int id);

    /**
     * 校验email
     * @param email
     * @return
     */
    public Message checkUserByEmail(String email);

    /**
     * update校验email
     * @param email
     * @return
     */
    public Message checkEmail(String email,int id);
    /**
     * 用户分页
     */
    public Message getUserPage(String userName,int roleId,int currentPageNo,int pageSize);

    /**
     * 用户删除（逻辑）
     */
    public Message delUser(int id,int roleId);


    /**
     * 校验用户有没有权限修改（逻辑）
     */
    public Message checkUserUpdate(int id,int roleId);

    /**
     * 通过id查找用户
     */
    public Message getUserById(int id);

    /**
     * 通过登录用户查找用户
     */
    public Message getUser();

    /**
     * 通过id查找用户
     */
    public Message updateUser(User user);

    Message checkMobile(String mobile, int id);
}
