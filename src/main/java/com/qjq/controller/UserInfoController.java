package com.qjq.controller;

import com.qjq.dao.UserMapper;
import com.qjq.exception.CustomException;
import com.qjq.po.*;
import com.qjq.service.impl.LoginServiceImpl;
import com.qjq.service.impl.UserServiceImpl;
import com.qjq.utils.NewStringUtil;
import com.qjq.utils.PasswordMD5;
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
public class UserInfoController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    LoginServiceImpl loginService;
    @RequestMapping(value = "/userInfo.action",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String userInfo(HttpSession httpSession){
        User user=(User)httpSession.getAttribute("user");
        return user.getName();
    }
    @RequestMapping("changePassword.action")
    @ResponseBody
    public Map<String,String> changePassword(Model model, HttpSession session,String oldPass,String newPass) {
        String userId=(String)session.getAttribute("userID");
        System.out.println("oldpass"+oldPass);
        System.out.println("userID"+userId);
        HashMap<String, String> map = new HashMap<String, String>();
        try {
            loginService.findUserByIDAndPassword(userId,oldPass);
        } catch (CustomException e) {
            map.put("msg","原密码错误！");
            map.put("code","200");
            return map;
        }
        User user=userService.findUserByID(userId);
        user.setPassword(PasswordMD5.createPassword(newPass));
        userService.updateUser(user);
        map.put("code","0");
        map.put("msg","已修改");
        return map;
    }
    @RequestMapping("changeHeadPhoto.action")
    @ResponseBody
    public Map<String,String> changeHeadPhoto(Model model, HttpSession session,String headPhoto) {
        String userId=(String)session.getAttribute("userID");
        HashMap<String, String> map = new HashMap<String, String>();
        User user=userService.findUserByID(userId);
        user.setHeadPhoto(headPhoto);
        userService.updateUser(user);
        map.put("code","0");
        map.put("msg","头像已更换");
        return map;
    }
    @RequestMapping("refreshUser.action")
    @ResponseBody
    public Map<String,String> refreshUser(Model model, HttpSession session,String userId) {
        HashMap<String, String> map = new HashMap<String, String>();
        User user=userService.findUserByID(userId);
        session.setAttribute("user",user);
        map.put("code","0");
        return map;
    }
    @RequestMapping("deleteUser.action")
    @ResponseBody
    public Map<String,String> deleteUser(Model model, HttpSession session,String id) {
        HashMap<String, String> map = new HashMap<String, String>();
        userService.deleteUserById(id);
        map.put("code","0");
        map.put("msg","注销用户成功！");
        return map;
    }
    @RequestMapping("disableUser.action")
    @ResponseBody
    public Map<String,String> disableUser(Model model, HttpSession session,String id) {
        HashMap<String, String> map = new HashMap<String, String>();
        userService.disableUserById(id);
        map.put("code","0");
        map.put("msg","禁言用户成功！");
        return map;
    }
    @RequestMapping("changeSignature.action")
    @ResponseBody
    public Map<String,String> changeSignature(Model model, HttpSession session,String signature) {
        String userId=(String)session.getAttribute("userID");
        HashMap<String, String> map = new HashMap<String, String>();
        User user=userService.findUserByID(userId);
        user.setSignature(signature);
        userService.updateUser(user);
        map.put("code","0");
        map.put("msg","已保存");
        return map;
    }
    @RequestMapping("updateUser.action")
    @ResponseBody
    public Map<String,String> updateUser(Model model, HttpSession session,String id,String institute,String phone) {
       User user=userService.findUserByID(id);
       user.setPhone(phone);
       user.setInstitute(institute);
       HashMap<String, String> map = new HashMap<String, String>();
       userService.updateUser(user);
       map.put("code","0");
       map.put("msg","已保存");
       return map;
    }
    @RequestMapping("getUserDetail.action")
    @ResponseBody
    public User getUserDetail(HttpSession session, @Param("id") String id){
        User user= userService.findUserByID(id);
        return user;
    }
    @RequestMapping("usersMsg.action")
    @ResponseBody
    public Map<String,Object> usersMsg(HttpSession session, int page, int limit,String id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<User> users=new LinkedList<User>();
        int rows = userService.getCountofUsers();
        if(id==null||id==""){
            Page bpage=new Page();
            bpage.setPageSize(limit);
            bpage.setCurrentPage(page);
            bpage.setRows(rows);
            users = userService.selectUserByPage(bpage);
        }else{
            User student=userService.findUserByID(id);
            users.add(student);
        }
        map.put("code","0");
        map.put("data",users);
        map.put("msg","");
        map.put("count",rows);
        return map;
    }
}
