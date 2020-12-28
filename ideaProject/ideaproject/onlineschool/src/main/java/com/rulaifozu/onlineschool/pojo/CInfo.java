package com.rulaifozu.onlineschool.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
//课程信息表
@Data
public class CInfo {
    //@TableId(value = "cNo")
    private String cNo;
    private String cName;
    private String cType;//种类
    private String cIntro;//介绍
    private String cVideo;//视频url
    private String cPic;//封面url
    private int cMoney;
    private int cStatus;//课程状态 0未通过 1已发布 2审核中
    private Release release_id;//发布信息
    private CViewInfo cViewInfo_id;//视频其他信息

}
