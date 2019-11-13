package com.qjq.controller;

import com.qjq.po.Bulletin;
import com.qjq.po.Page;
import com.qjq.po.Student;
import com.qjq.service.StudentService;
import com.qjq.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    @RequestMapping("StudentsMsg.action")
    @ResponseBody
    public Map<String,Object> StudentsMsg(HttpSession session, int page, int limit,String id){
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<Student>students=new LinkedList<Student>();
        int rows = studentService.getCountofStudents();
        if(id==null||id==""){
            Page bpage=new Page();
            bpage.setPageSize(limit);
            bpage.setCurrentPage(page);
            bpage.setRows(rows);
            students = studentService.selectAllStudents(bpage);
        }else{
            Student student=studentService.selectStudentByID(id);
            students.add(student);
        }
        System.out.println(students);
        map.put("code","0");
        map.put("data",students);
        map.put("msg","");
        map.put("count",rows);
        return map;
    }
    @RequestMapping("deleteStudent.action")
    @ResponseBody
    public Map<String,String> deleteStudent(HttpSession session,String id){
        HashMap<String, String> map = new HashMap<String, String>();
        studentService.deleteStudent(id);
        System.out.println(id);
        map.put("code","0");
        map.put("msg","删除成功");
        return map;
    }

}
