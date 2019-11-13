package com.qjq.dao;

import com.qjq.po.Bulletin;
import com.qjq.po.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BulletinMapper {
    public List<Bulletin> selectAllBulletins(@Param("page") Page page);
    public int findRows();
    public Bulletin selectBulletinByID(@Param("id") String id);
    public int addBulletin(@Param("bulletin") Bulletin bulletin);
    public List<Bulletin> selectBulletinsByKey(@Param("key")String key);
}