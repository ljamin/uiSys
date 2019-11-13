package com.qjq.service;

import com.qjq.exception.CustomException;
import com.qjq.po.Page;
import com.qjq.po.Student;
import com.qjq.po.User;


import java.util.List;

public interface StudentService {

    User findStudentByIDAndNameAndPhone( String id,String name, String phone) throws CustomException;
    int getCountofStudents();
    List<Student> selectAllStudents( Page page);
    void deleteStudent(String id);

    public Student selectStudentByID(String id);

}
