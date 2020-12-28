package com.rulaifozu.onlineschool.pojo;

import lombok.Data;
//课程浏览数据表
@Data
public class CViewInfo {
    private int id;
    private int cLike;//点赞量
    private int cComment;//评论量
    private int cPageView;//浏览量
}
