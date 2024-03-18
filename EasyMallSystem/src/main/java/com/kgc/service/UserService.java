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
