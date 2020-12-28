package com.rulaifozu.onlineschool.service.impl;

import com.rulaifozu.onlineschool.config.SeedUtil;
import com.rulaifozu.onlineschool.dao.*;
import com.rulaifozu.onlineschool.pojo.*;
import com.rulaifozu.onlineschool.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    CInfoDao cInfoDao;

    @Autowired
    CViewInfoDao cViewInfoDao;

    @Autowired
    ReleaseDao releaseDao;

    @Autowired
    UInfoDao uInfoDao;

    @Autowired
    LikeDao likeDao;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

    // 根据id查找课程信息
    @Override
    public CInfo getCInfoByCNo(String id) {
        return cInfoDao.findCInfoByCNo(id);
    }

    @Override
    public List<CInfo> findCInfoByTypeOrCName(String search) {
        return cInfoDao.findCInfoByTypeOrCName(search);
    }

    @Override
    public List<CInfo> findCInfoByType(String search) {
        return cInfoDao.findCInfoByType(search);
    }

    @Override
    public List<CInfo> findCInfoByCPageView() {

        return cInfoDao.findCInfoByCPageView();
    }

    @Transactional
    @Override
    public int addCInfo(CInfo cInfo,int uId) throws Exception {
        Date date = df.parse(df.format(new Date()));

        UInfo uInfo=uInfoDao.findUInfoByUId(uId);
        Release release=new Release();
        release.setUInfo_id(uInfo);
        release.setRTime(date);
        releaseDao.addRelease(release);

        CViewInfo cViewInfo= new CViewInfo();
        cViewInfo.setCComment(0);
        cViewInfo.setCLike(0);
        cViewInfo.setCPageView(0);
        cViewInfoDao.addCViewInfo(cViewInfo);

        cInfo.setCNo(SeedUtil.getId());
        cInfo.setCStatus(2);
        cInfo.setRelease_id(release);
        cInfo.setCViewInfo_id(cViewInfo);

        cInfoDao.addCInfo(cInfo);

        return 0;
    }

    @Override
    public List<CInfo> findReCInfoBYUser(int uId) {
        return cInfoDao.findReCInfoBYUser(uId);
    }

    @Override
    public List<CInfo> findSubCInfoBYUser(int uId) {
        return cInfoDao.findSubCInfoBYUser(uId);
    }

    @Override
    public List<CInfo> findReCInfoBYUserStatus0(int uId) {
        return cInfoDao.findReCInfoBYUserStatus0(uId);
    }

    @Override
    public List<CInfo> findReCInfoBYUserStatus2(int uId) {
        return cInfoDao.findReCInfoBYUserStatus2(uId);
    }

    @Override
    public List<CInfo> findReCInfoBYUserStatus1(int uId) {
        return cInfoDao.findReCInfoBYUserStatus1(uId);
    }

    @Override
    public List<CInfo> findCInfoBYStatus2() {
        return cInfoDao.findCInfoBYStatus2();
    }

    @Override
    public int deleteCInfoByCNo(String cno) {
        return 0;
    }

    @Override
    public List<CInfo> findCInfoByNameInType(String search, String type) {
        return cInfoDao.findCInfoByNameInType(search,type);
    }

    @Override
    public int updateCInfoStatus(Map<String, Object> map) {
        int status= (Integer) map.get("status");
        String cno= (String) map.get("cno");
        return cInfoDao.updateCInfoStatus(status,cno);
    }

    @Transactional
    @Override
    public int like(String cno, UInfo uInfo) throws ParseException {
        cViewInfoDao.addLikeNum(cno);
        Like like = new Like();
        like.setCInfo_id(cInfoDao.findCInfoByCNo(cno));
        like.setUInfo_id(uInfo);
        like.setLikeTime(new Date());
        likeDao.addLike(like);

        return 0;
    }

    @Override
    public void addCPageView(String cno) {
        cViewInfoDao.addCPageView(cno);
    }
}
