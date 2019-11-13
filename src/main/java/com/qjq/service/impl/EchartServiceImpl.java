package com.qjq.service.impl;

import com.qjq.dao.NewsMapper;
import com.qjq.dao.StudentMapper;
import com.qjq.dao.UserMapper;
import com.qjq.service.EchartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class EchartServiceImpl implements EchartService {
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    StudentMapper studentMapper;
    @Override
    public List getCountfoNewsByTypes() {
        List counts=new LinkedList();
        counts.add(newsMapper.getCountofNewsByType(0));
        counts.add(newsMapper.getCountofNewsByType(1));
        counts.add(newsMapper.getCountofNewsByType(2));
        counts.add(newsMapper.getCountofNewsByType(3));
        counts.add(newsMapper.getCountofNewsByType(4));
        counts.add(newsMapper.getCountofNewsByType(5));
        return counts;
    }

    @Override
    public List getCountUserData() {
        List counts=new LinkedList();
        int studentCount=studentMapper.getCountofStudents();
        int userCount=userMapper.getCountofUsers();
        int unregisterStudent=studentCount-userCount;
        counts.add(studentCount);
        counts.add(userCount);
        counts.add(unregisterStudent);
        return counts;
    }
}
