package com.qjq.service.impl;

import com.qjq.dao.NewsMapper;
import com.qjq.dao.RemindMapper;
import com.qjq.po.News;
import com.qjq.po.Remind;
import com.qjq.service.RemindService;
import com.qjq.vo.RemindVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.enterprise.inject.New;
import java.util.LinkedList;
import java.util.List;

@Service
public class RemindServiceImpl implements RemindService {
    @Autowired
    RemindMapper remindMapper;
    @Autowired
    NewsMapper newsMapper;
    @Override
    public void addRemind(Remind remind) {
        //提醒类型是举报的话，消息的举报数更新
        System.out.println("remind.getRemindType()="+remind.getRemindType());
        if(remind.getRemindType()==2){
            News news=newsMapper.selectNewsByID(remind.getContentid());
            news.setAgainstnum(news.getAgainstnum()+1);
            newsMapper.updateNews(news);
        }
        remindMapper.addRemind(remind);
    }

    @Override
    public List<RemindVo> selectRemindVoByUserID(String userId) {
        return remindMapper.selectRemindVoByUserID(userId);
    }

    @Override
    public List<RemindVo> selectRemindVoByNewsID(List<RemindVo> remindVos, String newsID) {
        List<RemindVo> newRemindVos=new LinkedList<RemindVo>();
        for(int i=0;i<remindVos.size();i++)
        {
            if(remindVos.get(i).getToNewsId().equals(newsID))
            {
                newRemindVos.add(remindVos.get(i));
            }
        }
        return newRemindVos;
    }

    @Override
    public List<RemindVo> selectRemindVoByTypeId(Integer typeId) {
        return remindMapper.selectRemindVoByTypeId(typeId);
    }

    @Override
    public void changeRemindStatusById(String id) {
        Remind remind=remindMapper.selectRemindById(id);
        if(remind.getReadstatus()==0)
        {
            remind.setReadstatus(1);
            remindMapper.updateRemind(remind);
        }
    }
}
