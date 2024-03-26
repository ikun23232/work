package com.kgc.controller;

import com.alibaba.fastjson.JSON;
import com.kgc.entity.Address;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.AddressService;
import com.kgc.utils.UserSessionUtil;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: 欧洋宏
 * @create: 2024-03-24 13:11
 **/
@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;


    /**
     * 查登录用户的默认的地址
     * 公共
     * @return
     */
    @RequestMapping("getAddressById")
    public Message getAddressById(){
        //写死
        int userId = UserSessionUtil.getUserId();
        Message addressById = addressService.getAddressById(userId);
        return addressById;
    }

    /**
     * 查询当前用户所有地址
     * 公共
     * @return
     */
    @RequestMapping("/getAddresssList")
    public Message getAddresssList(@RequestBody Map map){

        Page page = JSON.parseObject(JSON.toJSONString(map.get("page")), Page.class);
        String addressDetail = JSON.parseObject(JSON.toJSONString(map.get("addressDetail")), String.class);

        int userId = UserSessionUtil.getUserId();
        Message addresssList = addressService.getAddresssList(userId,page,addressDetail);
        return addresssList;
    }

    /**
     * 用户设置默认地址
     * @param id
     * @return
     */
    @RequestMapping("/setAddressDefault")
    public Message setAddressDefault(int id){
        Message message = addressService.setAddressDefault(id);
        return message;

    }


    /**
     * 用户删除地址
     * 删除地址
     * 公共
     * @param id
     * @return
     */
    @RequestMapping("/delAddressById")
    public Message delAddressById(int id){
        Message message = addressService.delAddressById(id);
        return message;
    }

    /**
     * 用户修改自己地址
     * 公共
     * @param address
     * @return
     */
    @RequestMapping("/updateAddressById")
    public Message updateAddressById(@RequestBody Address address){
        Message message = addressService.updateAddressById(address);
        return message;
    }

    /**
     * 用户添加地址
     * 公共
     * @param address
     * @return
     */
    @RequestMapping("/addAddress")
    public Message addAddress(@RequestBody Address address){
        Message message = addressService.addAddress(address);
        return message;
    }

    /**
     * 根据id查地址
     * @param id
     * @return
     */
    @RequestMapping("/getAddressByid")
    public Message getAddressByid(int id) {
        Message addressByid = addressService.getAddressByid(id);
        return Message.success(addressByid);
    }
}
