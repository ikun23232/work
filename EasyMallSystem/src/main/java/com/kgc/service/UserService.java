package com.kgc.service;

import com.kgc.entity.Message;
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
     *修改密码界面检查用户名存在
     */
    public Message checkUserByLoginName(String loginName);

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
     * 校验email
     * @param email
     * @return
     */
    public Message checkUserByEmail(String email);
}
