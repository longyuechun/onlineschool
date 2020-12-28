package com.rulaifozu.onlineschool.pojo;

import lombok.Data;

import java.util.Date;
//点赞表
@Data
public class Like {
    private int likeId;
    private CInfo cInfo_id;
    private UInfo uInfo_id;
    private Date likeTime;
}
