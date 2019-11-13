package com.qjq.service.impl;

import com.qjq.dao.StudentMapper;
import com.qjq.po.Student;
import com.qjq.service.UploadExcelService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadExcelServiceImpl implements UploadExcelService {
    @Autowired
    StudentMapper studentMapper;
    @Override
    public List<Student> javaPoi(String excelPath) throws IOException {
        List<Student> studentList=readXls(excelPath);
        for(int i = 0;i<studentList.size();i++){
            Student student=new Student();
            student=studentList.get(i);
            studentMapper.replaceInsertStudent(student);
        }
        return studentList;
    }

    @Override
    public List<Student> readXls(String excelPath) throws IOException {
        InputStream is = new FileInputStream(excelPath);
        XSSFWorkbook XSSFWorkbook = new XSSFWorkbook(is);
        Student student = null;
        List<Student> list = new ArrayList<>();

        for (int numSheet = 0; numSheet < XSSFWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet XSSFSheet = XSSFWorkbook.getSheetAt(numSheet);
            if (XSSFSheet == null) {
                continue;
            }

            for (int rowNum = 1; rowNum <= XSSFSheet.getLastRowNum(); rowNum++) {
                XSSFRow XSSFRow = XSSFSheet.getRow(rowNum);
                if (XSSFRow != null) {
                    student = new Student();
                    XSSFCell id = XSSFRow.getCell(0);
                    XSSFCell name = XSSFRow.getCell(1);
                    XSSFCell phone = XSSFRow.getCell(2);
                    XSSFCell institute = XSSFRow.getCell(3);
                   // System.out.println(String.valueOf(Integer.parseInt(getValue(id))));
                    student.setId(String.valueOf(getValue(id)));
                    student.setName(getValue(name));
                    student.setPhone(getValue(phone));
                    student.setInstitute(getValue(institute));
                    list.add(student);
                }
            }
        }
        return list;
    }

    @Override
    public String getValue(XSSFCell XSSFCell) {
        if (XSSFCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {

            System.out.println("boolean");
            return String.valueOf(XSSFCell.getBooleanCellValue());
        } else if (XSSFCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
     //获取单元格的原始数据，用DecimalFormat对unmeric进行格式化
            DecimalFormat df = new DecimalFormat("0");
            String cellText = df.format(XSSFCell.getNumericCellValue());
            System.out.println("numeric"+cellText);
            return cellText;
        } else {
            System.out.println("string");
            return String.valueOf(XSSFCell.getStringCellValue());
        }
    }

}
