package com.qjq.dao;

import com.qjq.po.Comments;
import org.apache.ibatis.annotations.Param;

public interface CommentsMapper {
    int insert(Comments record);

    void deleteCommentsById(@Param("id")String id);

    void deleteCommentsByNewsId(@Param("newsId")String newsId);

}