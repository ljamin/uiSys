package com.qjq.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewStringUtil {
    public static String getStringID(){
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        int index = (int)(900 * Math.random())+100;
        String id = format.format(new Date()) + index;
        return id;
    };
    public static String getCreatetime(){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(new Date());
        return dateStr;
    }
}
