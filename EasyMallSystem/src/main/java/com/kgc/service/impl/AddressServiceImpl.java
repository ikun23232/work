package com.kgc.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.dao.AddressDao;
import com.kgc.entity.Address;
import com.kgc.entity.Message;
import com.kgc.entity.Page;
import com.kgc.service.AddressService;
import com.kgc.utils.UserSessionUtil;
import com.sun.org.apache.bcel.internal.generic.I2F;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: 欧洋宏
 * @create: 2024-03-24 12:25
 **/
@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;

    @Override
    public Message getAddressById(int userId) {
        Address addressById = addressDao.getAddressById(userId);
        if (addressById==null){
            return Message.error("无数据");
        }
      return Message.success(addressById);
    }

    @Override
    public Message getAddresssList(int userId,Page page,String addressDetail) {
        PageHelper.startPage(page.getCurrentPageNo(),page.getPageSize());
        List<Address> addresssList = addressDao.getAddresssList(userId,addressDetail);
        PageInfo pageInfo=new PageInfo<>(addresssList);
        return Message.success(pageInfo);
    }

    @Override
    public Message setAddressDefault(int id) {
        //用户
        int userId = UserSessionUtil.getUserId();
        int i = addressDao.updateAddressIsDefaultAll(userId);
        int i1 = addressDao.setAddressDefault(id);

        return Message.success("添加成功");
    }

    @Override
    public Message delAddressById(int id) {
        Address addressByid = addressDao.getAddressByid(id);
        if(addressByid.getIsDefault()==1){
            return Message.error("删除失败,默认地址不能删除！");
        }
        int updateRow = addressDao.delAddressById(id);
        if (updateRow<=0){
            return Message.error("删除失败");

        }
        return Message.success("删除成功");
    }

    @Override
    public Message updateAddressById(Address address) {
        int updateRow = addressDao.updateAddressById(address);
        if (updateRow<=0){
            return Message.error("修改失败");

        }
        return Message.success("修改成功");
    }

    @Override
    public Message addAddress(Address address) {
        int userId = UserSessionUtil.getUserId();
        address.setUserId(userId);
        Address addressById = addressDao.getAddressById(userId);
        if(addressById==null){
            int updateRow = addressDao.addFristAddress(address);
            if (updateRow<=0){
                return Message.error("添加失败");
            }
        }else {
            int updateRow = addressDao.addAddress(address);
            if (updateRow<=0){
                return Message.error("添加失败");
            }
        }
        return Message.success("添加成功");
    }

    @Override
    public Message getAddressByid(int id) {
        Address addressByid = addressDao.getAddressByid(id);
        return Message.success(addressByid);
    }
}
