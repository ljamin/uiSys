package com.qjq.dao;

import com.qjq.po.Great;
import org.apache.ibatis.annotations.Param;

public interface GreatMapper {
    int insert(Great great);

    int delete(Great great);

    int insertSelective(Great record);

    void deleteGreatByNewsId(@Param("newsId") String newsId);
}