package com.rulaifozu.onlineschool.dao;

import com.rulaifozu.onlineschool.pojo.UMoneyInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UMoneyInfoDao {
    public UMoneyInfo findMoneyById();

    int addUMoneyInfo(UMoneyInfo uMoneyInfo);

    int findMoney(int uId);

    int updateMoneyByUid(int id, int money);
}
