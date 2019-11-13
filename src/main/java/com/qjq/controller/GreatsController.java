package com.qjq.controller;

import com.qjq.exception.CustomException;
import com.qjq.po.Comments;
import com.qjq.po.Great;
import com.qjq.service.impl.GreatServiceImpl;
import com.qjq.utils.NewStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class GreatsController {
    @Autowired
    GreatServiceImpl greatService;

    @RequestMapping(value = "/addGreat.action")
    @ResponseBody
    public Map<String,String> addGreat(Model model, HttpSession httpSession, Great great) {
        HashMap<String, String> map = new HashMap<String, String>();
        //在great表中添加这条赞记录，该消息的点赞数加一
        try {
            greatService.addGreat(great);
        } catch (CustomException e) {
            map.put("msg",e.getMessage());
            map.put("code","200");
            return map;
        }
        map.put("code","0");
        map.put("msg","点赞成功");
        return map;
    }
    @RequestMapping(value = "/deleteGreat.action")
    @ResponseBody
    public Map<String,String> deleteGreat(Model model, HttpSession httpSession, Great great) {

        HashMap<String, String> map = new HashMap<String, String>();
        System.out.println(great);
        try {
            greatService.deleteGreat(great);
        } catch (CustomException e) {
            map.put("msg",e.getMessage());
            return map;
        }
        map.put("msg","取消成功");
        return map;
    }
}
