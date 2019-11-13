package com.qjq.dao;

import com.qjq.po.Page;
import com.qjq.po.Student;
import com.qjq.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {

    User findStudentByIDAndNameAndPhone(@Param("user") User user);

    //从Excel表中更新数据时，存在则更新（删除后插），不存在则插入
    void replaceInsertStudent(@Param("student")Student student);
    //查找行数，用于管理员查看学生信息时分页
    int getCountofStudents();
    List<Student> selectAllStudents(@Param("page")Page page);
    //管理员删除学生信息
    void deleteStudent(@Param("id") String id);
    Student selectStudentByID(String id);
    int insert(Student record);
    int insertSelective(Student record);

}