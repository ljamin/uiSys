package com.qjq.service;

import com.qjq.po.Comments;

public interface CommentsService {
    void deleteCommentsById(String id,String toNewsId);
    void addComment(Comments comment);
}
