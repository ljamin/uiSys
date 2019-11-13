package com.qjq.dao;

import com.qjq.po.Reply;

public interface ReplyMapper {
    int insert(Reply record);

    int insertSelective(Reply record);
}