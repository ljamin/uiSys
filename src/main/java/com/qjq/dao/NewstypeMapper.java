package com.qjq.dao;

import com.qjq.po.Newstype;

public interface NewstypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Newstype record);

    int insertSelective(Newstype record);

    Newstype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Newstype record);

    int updateByPrimaryKey(Newstype record);
}