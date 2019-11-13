package com.qjq.controller;

import com.qjq.dao.UserMapper;
import com.qjq.exception.CustomException;
import com.qjq.po.User;
import com.qjq.service.impl.LoginServiceImpl;
import com.qjq.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private UserMapper userMapper=null;
    @Autowired
    private LoginServiceImpl loginService;

    /**
     * 进入登入页面：
     * 使用ModelAndView向前端页面传输数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login.action")
    public ModelAndView toLoginhtml(HttpServletRequest request , HttpServletResponse response){
        ModelAndView model=new ModelAndView();
        model.setViewName("login.html");
        return model;
    }

    /**
     * 更换验证码
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping("/changeCode.action")
    @ResponseBody
    public void getIdentifyingCode(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // 验证码存储在session的identifyingCode，属性中
        CaptchaUtil.outputCaptcha(request, response);
    }

    /**
     * 点击登入按钮，并进行验证码验证
     * 进入首页
     * @param model
     * @param httpSession
     * @param id
     * @param password
     * @param identifyingcode
     * @return
     */
    @RequestMapping(value = "/userLogin.action")
    @ResponseBody
    public Map<String,String> userLogin(Model model, HttpSession httpSession, String id,
                                            String password, String identifyingcode) {
        String code = (String) httpSession.getAttribute("identifyingCode");
        HashMap<String, String> map = new HashMap<String, String>();
        if (identifyingcode.equalsIgnoreCase(code)) {
            User user = null;
            try {
                user = loginService.findUserByIDAndPassword(id, password);
            } catch (CustomException e) {
                map.put("msg",e.getMessage());
                map.put("status","500");
                return map;
            }
          // 保存到session
            httpSession.setAttribute("user",user);
            httpSession.setAttribute("userID",user.getId());
            httpSession.setAttribute("roleID",user.getRoleId());
            httpSession.setAttribute("statusID",user.getStatusId());
            map.put("url","./loginSuccess.action");
            map.put("msg","登录成功，正在跳转……");
            map.put("status","200");
            return map;
       } else {
            map.put("msg", "验证码错误");
            map.put("status", "0");
            return map;
        }

    }

    /**
     * 登录成功
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/loginSuccess.action")
    public ModelAndView loginSuccess(HttpServletRequest request , HttpServletResponse response){
        ModelAndView model=new ModelAndView();
        model.setViewName("index.jsp");
        return model;
    }
    @RequestMapping("/toPage.action")
    public ModelAndView toPage(HttpServletRequest request , HttpServletResponse response,String url){
        ModelAndView model=new ModelAndView();
        model.setViewName(url);
        return model;
    }

    @RequestMapping(value = "/logout.action")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("UserId");
        return "redirect:/login.action";
    }
}
