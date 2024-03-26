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
    @RequestMapping("/checkUserByLoginName")
    public Message checkUserByLoginName(String loginName,String email) {
        logger.info("UserController addUser is start.....");
        logger.info("UserController userService addUser is start.....loginName" + loginName);
        Message message = userService.checkUserByLoginName(loginName,email);
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


    @RequestMapping("/loginto")
    public Message loginTo(String loginName,String password){
        logger.info("UserController loginTo is start......loginName:"+loginName+"password:"+password);
        Message message = userService.checkUserByNamePwd(loginName,password);
        return message;
    }

    @RequestMapping("/updatePassword")
    public Message updatePassword(String loginName,String password){
        logger.info("UserController loginTo is start......loginName:"+loginName+"password:"+password);
        Message message = userService.updatePassword(loginName,password);
        return message;
    }

    @RequestMapping("/getUserPage")
    public Message getUserPage(String userName,int roleId,int currentPageNo){
        logger.info("UserController loginTo is start......userName:"+userName+"roleId:"+roleId);
        Message message = userService.getUserPage(userName,roleId,currentPageNo,5);
        return message;
    }

    @RequestMapping("/delUser")
    public Message delUser(int id,int roleId){
        logger.info("UserController loginTo is start......id:"+id);
        Message message = userService.delUser(id,roleId);
        return message;
    }

    @RequestMapping("/checkUserUpdate")
    public Message checkUserUpdate(int id,int roleId){
        logger.info("UserController loginTo is start......id:"+id);
        Message message = userService.checkUserUpdate(id,roleId);
        return message;
    }

    @RequestMapping("/getUserById")
    public Message getUserById(int id){
        logger.info("UserController loginTo is start......id:"+id);
        Message message = userService.getUserById(id);
        return message;
    }

    @RequestMapping("/getUser")
    public Message getUser(){
        logger.info("UserController loginTo is start......");
        Message message = userService.getUser();
        return message;
    }
    @RequestMapping("/updateUser")
    public Message updateUser(User user){
        logger.info("UserController loginTo is start......user:"+user);
        Message message = userService.updateUser(user);
        return message;
    }

    @RequestMapping("/checkUserByUpdateName")
    public Message checkUserByUpdateName(String loginName,int id){
        logger.info("UserController loginTo is start......loginName:"+loginName+"id"+id);
        Message message = userService.checkUserByUpdateName(loginName,id);
        return message;
    }

    @RequestMapping("/checkUserByUpdateMobile")
    public Message checkUserByUpdateMobile(String mobile,int id){
        logger.info("UserController loginTo is start......mobile:"+mobile+"id"+id);
        Message message = userService.checkUserByUpdateMobile(mobile,id);
        return message;
    }

    @RequestMapping("/checkEmail")
    public Message checkEmail(String email,int id){
        logger.info("UserController loginTo is start......mobile:"+email+"id"+id);
        Message message = userService.checkEmail(email,id);
        return message;
    }


}
