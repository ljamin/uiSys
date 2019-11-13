package com.qjq.service;

import com.qjq.po.Bulletin;
import com.qjq.po.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BulletinService {
    public List<Bulletin> selectAllBulletins(Page page);
    public int findRows();
    public Bulletin selectBulletinByID(String id);
    int addBulletin( Bulletin bulletin);
    public List<Bulletin> selectBulletinsByKey(String key);
}
