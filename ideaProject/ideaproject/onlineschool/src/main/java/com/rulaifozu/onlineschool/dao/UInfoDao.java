package com.rulaifozu.onlineschool.dao;

import com.rulaifozu.onlineschool.pojo.UInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UInfoDao {
    public String findPassWordByUEmail(String uEmail);

    UInfo findUInfoByUId(int uId);

    int addUInfo(UInfo uInfo);

    UInfo findUInfoByEmail(String email);

    String  findIdByEmail(String email);

    int uptateUInfo(UInfo uInfo);
}
