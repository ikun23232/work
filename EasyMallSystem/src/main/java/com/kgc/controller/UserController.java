package com.kgc.controller;

import com.kgc.entity.Message;
import com.kgc.entity.User;
import com.kgc.service.UserService;
import com.kgc.utils.EmaiCodelUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * @author: 欧洋宏
 * @create: 2024-03-17 19:17
 **/
@RestController
public class UserController {
    private Logger logger = Logger.getLogger(getClass());
    @Autowired
    private UserService userService;

    @RequestMapping("test")
    public String test() {
        return "test";
    }

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    @RequestMapping("/addUser")
    public Message addUser(@RequestBody User user) {
        logger.info("UserController addUser is start.....");
        logger.info("UserController userService addUser is start.....user" + user);
        Message message = userService.addUser(user);
        logger.debug("UserController userService addUser is start.....user" + user + "message" + message);
        return message;
    }

    /**
     * 检查重名
     *
     * @param loginName
     * @return
     */
    @RequestMapping("/checkUserByName")
    public Message checkUserByName(String loginName) {
        logger.info("UserController addUser is start.....");
        logger.info("UserController userService addUser is start.....loginName" + loginName);
        Message message = userService.checkUserByName(loginName);
        logger.debug("UserController userService addUser is start.....loginName" + loginName + "message" + message);
        return message;
    }

    /**
     * 发送邮箱
     *
     * @param email
     */
    @RequestMapping("/sendEmailCode")
    public String sendEmailCode(String email) {
        Session session = EmaiCodelUtil.createSession();
        //	创建邮件对象
        MimeMessage message = new MimeMessage(session);
        String sixNum = EmaiCodelUtil.getSixNum();
        try {
            message.setSubject("主题");
            message.setText(sixNum);
            message.setFrom(new InternetAddress("2358560084@qq.com"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sixNum;
    }

    /**
     * 校验用户手机
     *
     * @param mobile
     * @return
     */
    @RequestMapping("/checkUserPhone")
    public Message checkUserPhone(String mobile) {
        logger.info("UserController addUser is start.....");
        logger.info("UserController userService addUser is start.....mobile" + mobile);
        Message message = userService.checkUserByMobile(mobile);
        logger.debug("UserController userService addUser is start.....mobile" + mobile + "message" + message);
        return message;
    }

    /**
     * 校验用户邮箱
     *
     * @param email
     * @return
     */
    @RequestMapping("/checkUserByEmail")
    public Message checkUserByEmail(String email) {
        logger.info("UserController addUser is start.....");
        logger.info("UserController userService addUser is start.....email" + email);
        Message message = userService.checkUserByEmail(email);
        logger.debug("UserController userService addUser is start.....email" + email + "message" + message);
        return message;
    }
}
