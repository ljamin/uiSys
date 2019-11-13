package com.qjq.service.impl;

import com.qjq.dao.UserMapper;
import com.qjq.exception.CustomException;
import com.qjq.po.Page;
import com.qjq.po.User;
import com.qjq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public void insertUser(User user) {
        userMapper.insertUser(user);
        System.out.println("userservice.roleid"+user.getRoleId());
        System.out.println("userservice.newnunn"+user.getNewsnum());

    }

    @Override
    public void selectUserByID(String id) throws CustomException {
        User user=userMapper.selectUserByID(id);
        //非空时存在，空时可注册
        if (user!=null)throw new CustomException("用户已存在，请前往登录");
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public User findUserByID(String id) {
        return userMapper.selectUserByID(id);
    }

    @Override
    public int getCountofUsers() {
        return userMapper.getCountofUsers();
    }

    @Override
    public List<User> selectUserByPage(Page page) {
        return userMapper.selectUserByPage(page);
    }

    @Override
    public void deleteUserById(String id) {
        userMapper.deleteUserByID(id);
    }

    @Override
    public void disableUserById(String id) {
        User user=userMapper.selectUserByID(id);
        if(user.getStatusId()==0){
            user.setStatusId(1);
        }else{
            user.setStatusId(0);
        }
        userMapper.updateUser(user);
    }
}
