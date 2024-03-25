package com.kgc.controller;

import com.kgc.entity.Address;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.AddressService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 欧洋宏
 * @create: 2024-03-24 13:11
 **/
@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;


    /**
     * 查默认的地址
     * @return
     */
    @RequestMapping("getAddressById")
    public Message getAddressById(){
        //写死
        int userId=22;
        Message addressById = addressService.getAddressById(userId);
        return addressById;
    }

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("/getAddresssList")

    public Message getAddresssList(@RequestBody Page page){
        int userId=22;
        Message addresssList = addressService.getAddresssList(userId,page);
        return addresssList;
    }

    /**
     * 设置默认地址
     * @param id
     * @return
     */
    @RequestMapping("/setAddressDefault")
    public Message setAddressDefault(int id){
        Message message = addressService.setAddressDefault(id);
        return message;

    }


    /**
     * 删除地址
     * @param id
     * @return
     */
    @RequestMapping("/delAddressById")
    public Message delAddressById(int id){
        Message message = addressService.delAddressById(id);
        return message;
    }

    /**
     * 修改地址
     * @param address
     * @return
     */
    @RequestMapping("/updateAddressById")
    public Message updateAddressById(@RequestBody Address address){
        Message message = addressService.updateAddressById(address);
        return message;
    }

    /**
     * 添加地址
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
