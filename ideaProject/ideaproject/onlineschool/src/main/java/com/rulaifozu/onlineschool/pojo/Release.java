package com.rulaifozu.onlineschool.pojo;

import lombok.Data;

import java.util.Date;
//用户课程发布表
@Data
public class Release {
    private int id;
    private UInfo uInfo_id;
    private Date rTime;
}
