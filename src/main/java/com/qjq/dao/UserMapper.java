package com.qjq.dao;

import com.qjq.po.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.qjq.po.User;

import java.util.List;

@Component
public interface UserMapper {
    //登录时确认密码
    User selectUserByIDAndPassword(@Param("user") User user);
    //注册时确认是否已注册
    User selectUserByID(@Param("id") String id);
    void deleteUserByID(@Param("id") String id);
    void insertUser(@Param("user")User user);
    void updateUser(@Param("user")User user);
    int getCountofUsers();
    List<User> selectUserByPage(@Param("page") Page page);
}