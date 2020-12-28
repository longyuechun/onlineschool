package com.rulaifozu.onlineschool.pojo;

import lombok.Data;

import java.util.Date;
//用户课程订阅表
@Data
public class Subscribe {
    private int sid;
    private Date stime;
    private String cinfo_id;
    private int uinfo_id;
}
