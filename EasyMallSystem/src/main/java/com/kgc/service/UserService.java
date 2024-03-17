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
     * 用户登录检查密码是否正确
     * @param loginName
     * @param password
     * @return
     */
    public Message checkUserByNamePwd(String loginName,String password);
}
