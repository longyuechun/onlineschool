package com.rulaifozu.onlineschool.pojo;

import lombok.Data;

import java.util.Date;
//用户信息表
@Data
public class UInfo {
    private int uId;
    private String uName;
    private String uPassword;
    private String uSex;
    private int uAge;
    private String uEmail;
    private String uPic;
    private UMoneyInfo uMoneyInfo_id;

}
