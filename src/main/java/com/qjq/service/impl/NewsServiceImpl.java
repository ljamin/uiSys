package com.qjq.service.impl;

import com.qjq.dao.*;
import com.qjq.po.News;
import com.qjq.po.User;
import com.qjq.service.NewsService;
import com.qjq.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    GreatMapper greatMapper;
    @Autowired
    RemindMapper remindMapper;
    @Override
    public void addNews(News news,String userId) {
        newsMapper.insert(news);
        User user=userService.findUserByID(userId);//user 新闻数增加
        user.setNewsnum(user.getNewsnum()+1);
        userService.updateUser(user);
    }

    @Override
    public List<News> selectNewsByUserID(String id) {
        return newsMapper.selectNewsByUserID(id);
    }

    @Override
    public News selectNewsByID(String id) {
        return newsMapper.selectNewsByID(id);
    }

    @Override
    public int findRows() {
        return newsMapper.findRows();
    }

    //删除news的同时去评论表，点赞表，提醒表删除相关记录
    @Override
    public void deleteNewsById(String id) {
        newsMapper.deleteNewsById(id);
        greatMapper.deleteGreatByNewsId(id);
        remindMapper.deleteRemindByNewsId(id);
        commentsMapper.deleteCommentsByNewsId(id);//先删除提醒表再删除评论表
    }

    @Override
    public List<NewsVo> selectNewsVoByTypeId(int typeId) {
        return newsMapper.selectNewsVoByTypeId(typeId);
    }


}
