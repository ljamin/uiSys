package com.qjq.controller;

import com.qjq.po.Comments;
import com.qjq.po.Remind;
import com.qjq.service.impl.CommentsServiceImpl;
import com.qjq.service.impl.RemindServiceImpl;
import com.qjq.utils.NewStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CommentsController {
    @Autowired
    CommentsServiceImpl commentsService;
    @Autowired
    RemindServiceImpl remindService;

    @RequestMapping(value = "/deleteComments.action")
    @ResponseBody
    //根据comments id删除
    public Map<String,String> deleteComments(Model model, HttpSession httpSession, String id,String toNewsId) {
        HashMap<String, String> map = new HashMap<String, String>();
        commentsService.deleteCommentsById(id,toNewsId);
        map.put("msg","已删除该评论");
        return map;
    }
    @RequestMapping(value = "/addComments.action")
    @ResponseBody
    public Map<String,String> addComments(Model model, HttpSession httpSession, Comments comment) {
        HashMap<String, String> map = new HashMap<String, String>();
        int status_id = (Integer) httpSession.getAttribute("statusID");
        if (status_id != 1) {//如果用户不是禁言状态,添加comments    0自由 1禁言
            String id = NewStringUtil.getStringID();
            comment.setId("c" + id);
            //添加评论记录
            commentsService.addComment(comment);
            //添加提醒记录
            String rid = NewStringUtil.getStringID();
            Remind remind = new Remind();
            remind.setId("r" + rid);
            remind.setRemindType(1);
            remind.setCreatetime(comment.getCreatetime());
            remind.setFromUserId(comment.getFromUserId());
            remind.setToUserId(comment.getToUserId());
            remind.setContentid(comment.getId());
            remindService.addRemind(remind);
            map.put("code", "0");
            map.put("msg", "评论成功");
            map.put("data",comment.getId());
            return map;
        } else {
            map.put("code", "500");
            map.put("msg", "您处于禁言状态，不可发表评论哦！");
            return map;
        }
    }
}
