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
}
