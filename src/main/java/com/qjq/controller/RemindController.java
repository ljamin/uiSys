package com.qjq.controller;

import com.qjq.po.Bulletin;
import com.qjq.po.Page;
import com.qjq.service.impl.RemindServiceImpl;
import com.qjq.vo.RemindVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RemindController {
    @Autowired
    RemindServiceImpl remindService;

    @RequestMapping("/showMyNotices.action")
    @ResponseBody
    public Map<String, Object> showMyNotices(HttpSession session, int page, int limit, String newsID) {
        String userId=(String)session.getAttribute("userID");
        System.out.println("myin");
        List<RemindVo> remindVos = remindService.selectRemindVoByUserID(userId);
        HashMap<String, Object> map = new HashMap<String, Object>();
        System.out.println(newsID);
        if (newsID == null || newsID == "") {
            int begin=(page-1)*limit;
            int end=begin+limit;
            int rend=remindVos.size()<end?remindVos.size():end;
            map.put("count", remindVos.size());
            map.put("data", remindVos.subList(begin,rend));
        } else {
            List<RemindVo> remindVos1 = remindService.selectRemindVoByNewsID(remindVos,newsID);
            map.put("count", remindVos1.size());
            map.put("data", remindVos1);
        }
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }
    @RequestMapping("/allNotices.action")
    @ResponseBody
    public Map<String, Object> allNotices(HttpSession session, int page, int limit, String newsID) {
        String userId=(String)session.getAttribute("userID");
        List<RemindVo> remindVos = remindService.selectRemindVoByTypeId(2);
        HashMap<String, Object> map = new HashMap<String, Object>();
        System.out.println(newsID);
        if (newsID == null || newsID.equals("")) {
            int begin=(page-1)*limit;
            int end=begin+limit;
            int rend=remindVos.size()<end?remindVos.size():end;
            map.put("count", remindVos.size());
            map.put("data", remindVos.subList(begin,rend));
        } else {
            List<RemindVo> remindVos1 = remindService.selectRemindVoByNewsID(remindVos,newsID);

            map.put("count", remindVos1.size());
            map.put("data", remindVos1);
        }
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }
}
//违规处理页面，已读未读处理，发布网站处理，毕业论文修改（换图片，加内容），PPT