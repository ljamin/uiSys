package com.qjq.service.impl;

import com.qjq.dao.UserMapper;
import com.qjq.exception.CustomException;
import com.qjq.po.User;
import com.qjq.service.LoginService;
import com.qjq.utils.PasswordMD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper=null;
    public User findUserByIDAndPassword(String id, String password)throws CustomException {
        User user=new User();
        user.setId(id);
        password= PasswordMD5.createPassword(password);//加密
        user.setPassword(password);

        User user1=userMapper.selectUserByIDAndPassword(user);

        if (user1==null) throw new CustomException("学号或密码错误！");
        return user1;
    }

}
