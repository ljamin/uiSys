package com.qjq.service;

import com.qjq.exception.CustomException;
import com.qjq.po.Page;
import com.qjq.po.User;

import java.util.List;

public interface UserService {
    void insertUser(User user);
    void selectUserByID(String id) throws CustomException;
    void updateUser(User user);
    User findUserByID(String id);
    int getCountofUsers();
    List<User> selectUserByPage(Page page);
    void deleteUserById(String id);
    void disableUserById(String id);
}
