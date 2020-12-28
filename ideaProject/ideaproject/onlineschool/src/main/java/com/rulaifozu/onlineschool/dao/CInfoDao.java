package com.rulaifozu.onlineschool.dao;

import com.rulaifozu.onlineschool.pojo.CInfo;
import com.rulaifozu.onlineschool.pojo.UInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CInfoDao {
    CInfo findCInfoByCNo(String id);

    List<CInfo> findCInfoByTypeOrCName(String search);

    List<CInfo> findCInfoByType(String search);

    List<CInfo> findCInfoByCPageView();

    int addCInfo(CInfo cInfo);

    List<CInfo> findReCInfoBYUser(int uId);

    List<CInfo> findSubCInfoBYUser(int uId);

    List<CInfo> findReCInfoBYUserStatus0(int uId);

    List<CInfo> findReCInfoBYUserStatus2(int uId);

    List<CInfo> findReCInfoBYUserStatus1(int uId);

    List<CInfo> findCInfoBYStatus2();

    List<CInfo> findCInfoByNameInType(String search, String type);

    int updateCInfoStatus(int status, String cno);
}
