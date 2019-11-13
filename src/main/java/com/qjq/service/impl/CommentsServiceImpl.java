package com.qjq.service.impl;

import com.qjq.dao.CommentsMapper;
import com.qjq.dao.NewsMapper;
import com.qjq.po.Comments;
import com.qjq.po.News;
import com.qjq.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    NewsMapper newsMapper;
    @Override
    public void deleteCommentsById(String id,String toNewsId) {
        commentsMapper.deleteCommentsById(id);
        News news=newsMapper.selectNewsByID(toNewsId);
        //消息的评论数-1
        news.setCommentsnum(news.getCommentsnum()-1);
        newsMapper.updateNews(news);
    }

    @Override
    public void addComment(Comments comment) {
        commentsMapper.insert(comment);
        News news=newsMapper.selectNewsByID(comment.getToNewsId());
        //消息的评论数加1
        news.setCommentsnum(news.getCommentsnum()+1);
        newsMapper.updateNews(news);
    }
}
