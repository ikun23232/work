package com.kgc.dao;

import com.kgc.entity.Role;

import java.util.List;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/23/ 11:27
 * @Description
 */
public interface RoleDao {

    public List<Role> getRoleList(int id);
}
