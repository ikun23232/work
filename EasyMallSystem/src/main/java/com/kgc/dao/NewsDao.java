package com.kgc.dao;

import com.kgc.entity.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsDao {
    public List<News> getNewList();

    public int addNewList(News news);
    public List<News> getNewListByPage(@Param("title") String title);
    public int delNew(int id);
    public int updateNew(News news);
    public News getNewById(int id);


}
