package com.qjq.controller;

import com.qjq.exception.CustomException;
import com.qjq.po.Student;
import com.qjq.po.User;
import com.qjq.service.impl.StudentServiceImpl;
import com.qjq.service.impl.UserServiceImpl;
import com.qjq.utils.GetCode;
import com.qjq.utils.NewStringUtil;
import com.qjq.utils.PasswordMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RegisterController {

    @Autowired
    StudentServiceImpl studentService;
    @Autowired
    UserServiceImpl userService;
    @RequestMapping("register.action")
    @ResponseBody
    public Map<String,String> register( HttpSession httpSession,String id,String name,
                                        String password,String phone){
        /*从student表中搜id name phone一致的信息，有则根据id在user表中查找是否已注册，
                        （有则返回500，无则插入user至user表，返回200信息，跳转到登录）
        * 无则返回0信息*/
        /*将信息存在User中，用User在student表中查找*/
        HashMap<String, String> map = new HashMap<String, String>();
        User user=new User();
        try {
            user=studentService.findStudentByIDAndNameAndPhone(id,name,phone);
        } catch (CustomException e) {
            /*student表中不存在该学生信息*/
            map.put("msg",e.getMessage());
            map.put("status","0");
            return map;
        }
        /*根据id在user表中查找*/
        try {
            userService.selectUserByID(id);
        } catch (CustomException e) {
            /*存在，返回存在信息*/
            map.put("msg",e.getMessage());
            map.put("status","500");
            return map;
        }
        String dateStr = NewStringUtil.getCreatetime();
        user.setRegistertime(dateStr);
        //密码加密
        password=PasswordMD5.createPassword(password);
        user.setPassword(password);
        userService.insertUser(user);
        map.put("msg","注册成功，请前往登录");
        map.put("status","200");
        map.put("url","./login.action");
        return map;
    }


    @RequestMapping("sendSMS.action")
    @ResponseBody
    public Map<String,String> sendSMS( HttpSession httpSession,String phone){
        String code= GetCode.getCode(phone);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("status","1");
        map.put("data",code);
        return map;
    }
}
