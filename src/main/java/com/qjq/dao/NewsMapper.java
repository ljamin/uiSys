package com.qjq.dao;

import com.qjq.po.News;
import com.qjq.vo.NewsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper {
    int insert(@Param("news") News news);

    int insertSelective(News news);

    List<News> selectNewsByUserID(@Param("id") String id);

    News selectNewsByID(@Param("id") String id);

    void deleteNewsById(@Param("id") String id);

    void updateNews(@Param("news")News news);

    List<NewsVo> selectNewsVoByTypeId(@Param("typeId") int typeId);

    int findRows();

    int getCountofNewsByType(@Param("typeId") int typeId);
}