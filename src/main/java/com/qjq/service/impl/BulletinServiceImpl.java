package com.qjq.service.impl;

import com.qjq.dao.BulletinMapper;
import com.qjq.po.Bulletin;
import com.qjq.po.Page;
import com.qjq.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BulletinServiceImpl implements BulletinService {
    @Autowired
    private BulletinMapper bulletinMapper;
    @Override
    public List<Bulletin> selectAllBulletins(Page page) {
        //List<Bulletin> bulletins= bulletinMapper.selectAllBulletins(page);
        return  bulletinMapper.selectAllBulletins(page);
    }

    @Override
    public int findRows() {
        return bulletinMapper.findRows();
    }

    @Override
    public Bulletin selectBulletinByID(String id) {
        return bulletinMapper.selectBulletinByID(id);
    }

    @Override
    public int addBulletin(Bulletin bulletin) {
        return bulletinMapper.addBulletin(bulletin);
    }

    @Override
    public List<Bulletin> selectBulletinsByKey(String key) {
        return bulletinMapper.selectBulletinsByKey(key);
    }
}
