package com.qjq.service;

import com.qjq.exception.CustomException;
import com.qjq.po.Great;

public interface GreatService {
    void addGreat(Great great)throws CustomException;
    void deleteGreat(Great great)throws CustomException;
}
