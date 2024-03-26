package com.kgc.dao;

import com.kgc.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressDao {
    /**
     * 查默认的地址
     * @return
     */
    public Address getAddressById(int userId);

    /**
     * 查询所有
     * @return
     */
    public List<Address> getAddresssList(@Param("userId") int userId,@Param("addressDetail") String addressDetail);

    /**
     * 设置默认地址
     * @param id
     * @return
     */
    public int setAddressDefault(int id);

    /**
     * 删除地址
     * @param id
     * @return
     */
    public int delAddressById(int id);

    /**
     * 修改地址
     * @param address
     * @return
     */
    public int updateAddressById(Address address);

    /**
     * 添加地址
     * @param address
     * @return
     */
    public int addAddress(Address address);

    public int updateAddressIsDefaultAll(int userId);
    public Address getAddressByid(int id);

}
