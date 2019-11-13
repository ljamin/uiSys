package com.qjq.dao;

import com.qjq.po.Remindtype;

public interface RemindtypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Remindtype record);

    int insertSelective(Remindtype record);

    Remindtype selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Remindtype record);

    int updateByPrimaryKey(Remindtype record);
}