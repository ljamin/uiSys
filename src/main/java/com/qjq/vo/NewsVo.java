package com.qjq.vo;

import com.qjq.po.Comments;
import com.qjq.po.Great;
import com.qjq.po.News;
import com.qjq.po.User;

import java.util.List;

public class NewsVo extends News {

    private User user;
    private List<Comments> comments;
    private List<Great> greats;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public List<Great> getGreats() {
        return greats;
    }

    public void setGreats(List<Great> greats) {
        this.greats = greats;
    }
}
