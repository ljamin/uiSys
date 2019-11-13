package com.qjq.service.impl;

import com.qjq.dao.GreatMapper;
import com.qjq.dao.NewsMapper;
import com.qjq.exception.CustomException;
import com.qjq.po.Great;
import com.qjq.po.News;
import com.qjq.service.GreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GreatServiceImpl implements GreatService {
    @Autowired
    GreatMapper greatMapper;
    @Autowired
    NewsMapper newsMapper;
    @Override
    public void addGreat(Great great)throws CustomException {
        int result;
        try {
            result = greatMapper.insert(great);
        } catch (Exception e) {
            System.out.println("捕获异常");
            throw new CustomException("您已经赞过啦！感谢喜欢");
        }
        //消息的likesnum+1
        News news=newsMapper.selectNewsByID(great.getNewsId());
        news.setLikesnum(news.getLikesnum()+1);
        newsMapper.updateNews(news);

    }

    @Override
    public void deleteGreat(Great great)throws CustomException {
        int result=greatMapper.delete(great);
        System.out.println("delete"+result);
        if(result==0)throw new CustomException("删除失败");
        //消息的likesnum-1
        News news=newsMapper.selectNewsByID(great.getNewsId());
        news.setLikesnum(news.getLikesnum()-1);
        newsMapper.updateNews(news);
    }
}
