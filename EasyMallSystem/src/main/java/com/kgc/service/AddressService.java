package com.kgc.service;

import com.kgc.entity.Address;
import com.kgc.entity.Message;
import com.kgc.entity.Page;

import java.util.List;

public interface AddressService {
    /**
     * 查默认的地址
     * @return
     */
    public Message getAddressById(int userId);

    /**
     * 查询所有
     * @return
     */
    public Message getAddresssList(int userId,Page page);

    /**
     * 设置默认地址
     * @param isDefault
     * @return
     */
    public Message setAddressDefault(int isDefault);

    /**
     * 删除地址
     * @param id
     * @return
     */
    public Message delAddressById(int id);

    /**
     * 修改地址
     * @param address
     * @return
     */
    public Message updateAddressById(Address address);

    /**
     * 添加地址
     * @param address
     * @return
     */
    public Message addAddress(Address address);

    public Message getAddressByid(int id);

}
