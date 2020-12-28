package com.rulaifozu.onlineschool.dao;

import com.rulaifozu.onlineschool.pojo.CViewInfo;
import com.rulaifozu.onlineschool.pojo.Release;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CViewInfoDao {
    CViewInfo findCViewInfoById();

    int addCViewInfo(CViewInfo cViewInfo);

    int addLikeNum(String cno);

    void addCPageView(String cno);
}
