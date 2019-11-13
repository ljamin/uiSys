package com.qjq.controller;

import com.qjq.po.News;
import com.qjq.po.Page;
import com.qjq.po.Remind;
import com.qjq.service.impl.NewsServiceImpl;
import com.qjq.service.impl.RemindServiceImpl;
import com.qjq.utils.NewStringUtil;
import com.qjq.vo.NewsVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class NewsController {
    @Autowired
    private NewsServiceImpl newsService;
    @Autowired
    RemindServiceImpl remindService;
    @RequestMapping(value="addNews.action",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addNews(News news, HttpSession session){
        int status_id=(Integer)session.getAttribute("statusID");
        if(status_id!=1){//如果用户不是禁言状态,添加news    0自由 1禁言
            String userId=(String)session.getAttribute("userID");
            String dateStr = NewStringUtil.getCreatetime();  //消息创建时间
            String id = NewStringUtil.getStringID();//用日期加三个随机数作为消息的id
            news.setCreatetime(dateStr);
            news.setId("n"+id);
            news.setFromUserId((String)session.getAttribute("userID"));
            news.setContent(news.getContent().replaceAll("\n", "<br/>"));
            news.setContent(news.getContent().replaceAll(" ", " &nbsp;"));
            newsService.addNews(news,userId);//将消息存入数据库
            return "发布成功！";
        }else{
            return "您正处于禁言状态，无法发布消息哦！有疑问请联系管理员";
        }



    }
    @RequestMapping("myNews.action")
    @ResponseBody
    public Map<String,Object> myNews(HttpSession session,int page,int limit){
        HashMap<String, Object> map = new HashMap<String, Object>();
        String userId=(String)session.getAttribute("userID");
        if(userId==null){
            map.put("msg","您已下线，请重新登录");
            map.put("code","500");
            map.put("url","./login.action");
        }else{
            List<News> newsList=newsService.selectNewsByUserID(userId);
            int begin=(page-1)*limit;
            int end=begin+limit;
            int rend=newsList.size()<end?newsList.size():end;
            map.put("code","0");
            map.put("msg","");
            map.put("data",newsList.subList(begin,rend));
            map.put("count",newsList.size());
        }
        return map;
    }

    @RequestMapping("getNewsDetail.action")
    @ResponseBody
    public News getNewsDetail(HttpSession session, @Param("id") String id,String remindId){
        System.out.println("RemindId="+remindId);
        News news= newsService.selectNewsByID(id);
        if(remindId!=null){
            //改变评论的状态
            remindService.changeRemindStatusById(remindId);
        }
        return news;
    }

    @RequestMapping("deleteNews.action")
    @ResponseBody
    public Map<String,String> deleteNews(HttpSession session,String id){
        HashMap<String, String> map = new HashMap<String, String>();
        newsService.deleteNewsById(id);
        System.out.println(id);
        map.put("code","0");
        map.put("msg","删除成功");
        return map;
    }

    @RequestMapping("showNewsByPage.action")
    public String showNewsByPage(Model model, HttpSession session, int typeId){
        List<NewsVo> newsVoList=newsService.selectNewsVoByTypeId(typeId);
        Page page=new Page();
        int rows=newsVoList.size();
        page.setRows(rows);
        session.setAttribute("newsVoList",newsVoList);
        session.setAttribute("page",page);
        session.setAttribute("rows",rows);
        return "showNews.jsp";
    }
    @RequestMapping("changePage.action")
    @ResponseBody
    public Map<String,Object> changePage(HttpSession session,int currentPage){
        HashMap<String, Object> map = new HashMap<String, Object>();
        Page page=(Page)session.getAttribute("page");
        int rows=(int)session.getAttribute("rows");
        page.setCurrentPage(currentPage);
        session.setAttribute("page",page);
        List<NewsVo> newsVos=(List<NewsVo>)session.getAttribute("newsVoList") ;
        int end=page.getBegin()+page.getPageSize();
        int rend=newsVos.size()<end?newsVos.size():end;
        List<NewsVo> dnewsVos=new LinkedList<NewsVo>(newsVos.subList(page.getBegin(),rend));
        map.put("data",dnewsVos);
        map.put("code","0");
        map.put("msg","");
        return map;
    }
    @RequestMapping("againstNews.action")
    @ResponseBody
    public Map<String,String> againstNews(HttpSession session,String userId,String newsId,String toUserId){
        HashMap<String, String> map = new HashMap<String, String>();
        //把举报添加到提醒表，更新消息表
        String rid = NewStringUtil.getStringID();
        String createtime=NewStringUtil.getCreatetime();
        Remind remind = new Remind();
        remind.setId("r"+rid);
        remind.setRemindType(2);
        remind.setCreatetime(createtime);
        remind.setFromUserId(userId);
        remind.setToUserId(toUserId);
        remind.setContentid(newsId);
        remindService.addRemind(remind);
        map.put("code","0");
        map.put("msg","举报成功");
        return map;
    }


}
