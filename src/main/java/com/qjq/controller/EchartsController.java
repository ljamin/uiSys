package com.qjq.controller;

import com.qjq.service.impl.EchartServiceImpl;
import com.qjq.service.impl.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class EchartsController {
    @Autowired
    private EchartServiceImpl echartService;
    @Autowired
    private NewsServiceImpl newsService;

    @RequestMapping("getNewsData.action")
    @ResponseBody
    public Map<String,Object> getNewsData(HttpSession session){
        HashMap<String, Object> map = new HashMap<String, Object>();
        List counts=new LinkedList();
        counts=echartService.getCountfoNewsByTypes();
        System.out.println(counts);
        map.put("data",counts);
        return map;
    }
    @RequestMapping("getUserData.action")
    @ResponseBody
    public Map<String,Object> getUserData(HttpSession session){
        HashMap<String, Object> map = new HashMap<String, Object>();
        List counts=new LinkedList();
        counts=echartService.getCountUserData();
        System.out.println(counts);
        map.put("data",counts);
        return map;
    }
}
