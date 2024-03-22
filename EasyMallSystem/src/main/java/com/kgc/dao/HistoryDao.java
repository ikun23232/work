package com.kgc.dao;

import com.kgc.entity.History;
import com.kgc.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author {喜吃大红袍}
 * @Date: 2024/03/20/ 15:33
 * @Description
 */
public interface HistoryDao {

    /**
     * 通过userId查找历史记录按时间排序
     */
    public List<Product> getHistoryListById(int userId);

    /**
     * 通过pid添加历史浏览记录
     */
    public int addHistoryById(@Param("pid") int pid, @Param("userId") int userId);

    /**
     * 删除历史浏览记录
     */
    public int delHistoryById(int id);

    /**
     * 通过pid,userId的查找history
     */
    public History getHistoryByPidUserId(@Param("pid")int pid,@Param("userId")int userId);

}
