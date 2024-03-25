package com.kgc.dao;

import com.kgc.entity.Concern;
import com.kgc.entity.Product;

import java.util.List;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/24/ 12:05
 * @Description
 */
public interface ConcernDao {

    public List<Concern> getConcernPageListES(int userId);
    public List<Product> getConcernPageList(int userId);
    public int addConcern(Concern concern);

    public int delConcern(int id);


}
