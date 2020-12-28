package com.rulaifozu.onlineschool.config;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        System.out.println(new Date());
        System.out.println(df.format(new Date()));
        Date date = df.parse(df.format(new Date()));
        System.out.println(date);
        System.out.println(Integer.parseInt("007"));
        String path = "C:\\onlineschoolresource\\video\\008.mp4";
        ProcessVideoUtil.processImg(path);
    }
}
