package com.qjq.controller;

import com.qjq.po.Bulletin;
import com.qjq.po.Page;
import com.qjq.service.impl.BulletinServiceImpl;
import com.qjq.utils.NewStringUtil;
import com.sun.org.apache.xml.internal.security.keys.storage.implementations.CertsInFilesystemDirectoryResolver;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class BulletinsController {
    @Autowired
    private BulletinServiceImpl bulletinService;

    @RequestMapping("SystemBulletins.action")
    @ResponseBody
    public Map<String, Object> SystemBulletins(HttpSession session, int page, int limit, String key) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<Bulletin> bulletins = new LinkedList<>();
        int rows = bulletinService.findRows();
        if (key == null || key == "") {
            Page bpage = new Page();
            bpage.setPageSize(limit);
            bpage.setCurrentPage(page);
            bpage.setRows(rows);
            System.out.println(bpage.getPageSize());
            bulletins = bulletinService.selectAllBulletins(bpage);
            map.put("count", rows);
        } else {
            bulletins = bulletinService.selectBulletinsByKey(key);
            map.put("count", bulletins.size());
        }
        map.put("data", bulletins);
        map.put("code", "0");
        map.put("msg", "");
        return map;
    }

    @RequestMapping("/getBulletinDetail.action")
    @ResponseBody
    public Bulletin getBulletinDetail(HttpSession session, @Param("id") String id) {
        Bulletin bulletin = bulletinService.selectBulletinByID(id);
        return bulletin;
    }

    @RequestMapping("addBulletin.action")
    @ResponseBody
    public String addBulletin(Bulletin bulletin, HttpSession session) {
        String dateStr = NewStringUtil.getCreatetime();  //公告创建时间
        String id= NewStringUtil.getStringID();//用日期加三个随机数作为公告的id
        bulletin.setCreatetime(dateStr);
        bulletin.setId("b"+id);
        bulletin.setContent(bulletin.getContent().replaceAll("\n", "<br/>"));
        bulletin.setContent(bulletin.getContent().replaceAll(" ", " &nbsp;"));
        bulletin.setFromUserId((String) session.getAttribute("userID"));
        bulletinService.addBulletin(bulletin);//将公告存入数据库
        System.out.println(bulletin.getTitle());
        return "success";
    }
}
