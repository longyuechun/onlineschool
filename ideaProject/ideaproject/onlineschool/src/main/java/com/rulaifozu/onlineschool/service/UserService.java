package com.rulaifozu.onlineschool.service;

import com.rulaifozu.onlineschool.pojo.UInfo;

import java.util.List;

public interface UserService {
    public int login (String email,String pword);

    UInfo findUInfoByUId(int uId);

    int register(UInfo uInfo);

    UInfo findUInfoByEmail(String email);

    int isRegister(String email);

    int uptateUInfo(UInfo uInfo);
}
