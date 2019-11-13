package com.qjq.service.impl;

import com.qjq.dao.StudentMapper;
import com.qjq.exception.CustomException;
import com.qjq.po.Bulletin;
import com.qjq.po.Page;
import com.qjq.po.Student;
import com.qjq.po.User;
import com.qjq.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Override
    public User findStudentByIDAndNameAndPhone(String id,String name, String phone) throws CustomException{
        User user=new User();
        user.setId(id);
        user.setName(name);
        user.setPhone(phone);
        User user1=studentMapper.findStudentByIDAndNameAndPhone(user);
        if (user1==null) throw new CustomException("学号或姓名或手机号信息有误！");
        return user1;
    }

    @Override
    public int getCountofStudents() {
        return studentMapper.getCountofStudents();
    }

    @Override
    public List<Student> selectAllStudents(Page page) {
        return studentMapper.selectAllStudents(page);
    }

    @Override
    public void deleteStudent(String id) {
         studentMapper.deleteStudent(id);
    }

    @Override
    public Student selectStudentByID(String id) {
        return studentMapper.selectStudentByID(id);
    }
}
