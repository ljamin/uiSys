package com.qjq.service;

import com.qjq.exception.CustomException;
import com.qjq.po.User;

public interface LoginService {
    public User findUserByIDAndPassword(String id, String password)throws CustomException;
}
