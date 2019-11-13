package com.qjq.service;

import com.qjq.po.News;
import com.qjq.vo.NewsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsService  {
     void addNews(News news,String userId);
     List<News> selectNewsByUserID(String id);
     News selectNewsByID(String id);
     int findRows();
     void deleteNewsById(@Param("id") String id);
     List<NewsVo> selectNewsVoByTypeId(int typeId);


}
