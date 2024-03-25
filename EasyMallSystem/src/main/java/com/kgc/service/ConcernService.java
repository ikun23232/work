package com.kgc.service;

import com.kgc.dao.ConcernDao;
import com.kgc.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/24/ 12:11
 * @Description
 */

public interface ConcernService {

    public Message getConcernPageList(int currentPageNo,int pageSize);

    public Message delConcern(int id);

    public Message addCercon(int productId);

}
