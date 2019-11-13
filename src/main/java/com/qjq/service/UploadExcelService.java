package com.qjq.service;

import com.qjq.po.Student;

import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.IOException;
import java.util.List;

public interface UploadExcelService {
    public List<Student> javaPoi(String excelPath)throws IOException;
    public List<Student> readXls(String excelPath)throws  IOException;
    public String getValue(XSSFCell xssfCell);
}
