package com.qjq.controller;

import com.qjq.service.impl.UploadExcelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {
    @Autowired
    UploadExcelServiceImpl uploadExcelService;
    @RequestMapping("upload.action")
    @ResponseBody
    public Map<String,Object> upload(HttpSession session, MultipartFile file, HttpServletRequest request,String type){
        // 文件后缀
        String prefix = "";
        // 时间前缀
        String dateStr = "";
        //保存上传
        OutputStream out = null;
        InputStream fileInput = null;
        // 随机数前缀
        int index = (int)(1000 * Math.random());
        //System.out.println("index="+index);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map = new HashMap<String, Object>();
        if (file != null) {
            String originalName = file.getOriginalFilename();
            prefix = originalName.substring(originalName.lastIndexOf(".") + 1);
            dateStr = format.format(new Date());
            String filepath = "";//文件本地路径
            String picPath="";//存入数据库的图片路径=
            String ufilepath="";//文件上传路径
            if (prefix.equals( "jpg") || prefix.equals("png")|| prefix.equals("jpeg") ) {
                filepath = request.getServletContext().getRealPath("/img/pic/") + dateStr + "/" + dateStr + index + "." + prefix;
                picPath="/img/pic/"+ dateStr + "/" + dateStr + index + "." + prefix;
                if(type!=null&&type.equals("headPhoto")){
                    filepath = request.getServletContext().getRealPath("/img/pic/head/") + dateStr + "/" + dateStr + index + "." + prefix;
                    picPath="/img/pic/head/"+ dateStr + "/" + dateStr + index + "." + prefix;
                }
            } else {
                filepath = request.getServletContext().getRealPath("/file/") + dateStr + "/" + dateStr + index + "." + prefix;
                picPath="/file/"+ dateStr + "/" + dateStr + index + "." + prefix;
            }
            ufilepath = filepath.replace("\\", "/");
            try {
                File files = new File(ufilepath);
                System.out.println("上传路径"+ufilepath);
                if (!files.getParentFile().exists()) {
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
            } catch (Exception e) {
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                        if (fileInput != null) {
                            fileInput.close();
                        }
                    } catch (IOException e) {
                    }
                }
            map2.put("src", picPath);
            System.out.println("保存进数据库的地址"+picPath);
            if(prefix.equals("xlsx") || prefix.equals("xls"))
            {
                try {
                    uploadExcelService.javaPoi(filepath);
                    System.out.println("excel上传文件地址 "+filepath);
                } catch (IOException e) {
                    System.out.println("更新失败");
                    e.printStackTrace();
                }
            }
        }
        map.put("code", "0");
        map.put("msg", "");
        map.put("data", map2);
        return map;

    }
}
