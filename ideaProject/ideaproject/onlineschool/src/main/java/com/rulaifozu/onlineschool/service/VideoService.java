package com.rulaifozu.onlineschool.service;

import com.rulaifozu.onlineschool.pojo.CInfo;
import com.rulaifozu.onlineschool.pojo.UInfo;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface VideoService {


    CInfo getCInfoByCNo(String id);

    List<CInfo> findCInfoByTypeOrCName(String search);

    List<CInfo> findCInfoByType(String search);

    List<CInfo> findCInfoByCPageView();

    int addCInfo(CInfo cInfo,int uId) throws Exception;

    List<CInfo> findReCInfoBYUser(int uId);

    List<CInfo> findSubCInfoBYUser(int uId);

    List<CInfo> findReCInfoBYUserStatus0(int uId);

    List<CInfo> findReCInfoBYUserStatus2(int uId);

    List<CInfo> findReCInfoBYUserStatus1(int uId);

    List<CInfo> findCInfoBYStatus2();

    int deleteCInfoByCNo(String cno);

    List<CInfo> findCInfoByNameInType(String search, String type);

    int updateCInfoStatus(Map<String, Object> map);

    int like(String cno, UInfo uInfo) throws ParseException;

    void addCPageView(String cno);
}
