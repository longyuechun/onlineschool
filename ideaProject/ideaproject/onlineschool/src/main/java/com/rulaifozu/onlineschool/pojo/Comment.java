package com.rulaifozu.onlineschool.pojo;

import lombok.Data;

import java.util.Date;
//评论表
@Data
public class Comment {
    private int cId;
    private CInfo cInfo_id;
    private int ParentId;
    private UInfo uInfo_id;
    private Date commTime;
    private String comms;//内容
}
