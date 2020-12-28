package com.rulaifozu.onlineschool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rulaifozu.onlineschool.config.Md5Utils;
import com.rulaifozu.onlineschool.dao.*;
import com.rulaifozu.onlineschool.pojo.*;
import com.rulaifozu.onlineschool.service.AdminService;
import com.rulaifozu.onlineschool.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class SubscribeServiceImpl extends ServiceImpl<SubscribeDao, Subscribe> implements SubscribeService {

    @Autowired
    SubscribeDao subscribeDao;

    @Autowired
    UInfoDao uInfoDao;

    @Autowired
    CInfoDao cInfoDao;

    @Autowired
    UMoneyInfoDao uMoneyInfoDao;
    @Override
    public String isBuy(String cno, int uId) {
        CInfo cInfo = cInfoDao.findCInfoByCNo(cno);
        int id = cInfo.getRelease_id().getId();
        if (id==uId){
            return "yes";
        }
        return subscribeDao.findCnoByCnoUId(cno,uId);
    }

    @Transactional
    @Override
    public int BuyCInfo(String cno, int uId) {

        CInfo cInfo = cInfoDao.findCInfoByCNo(cno);
        int money=cInfo.getCMoney();
        int buyerMoney=uMoneyInfoDao.findMoney(uId);
        if(buyerMoney<money){
            return 1;
        }
        int sellerId = cInfo.getRelease_id().getUInfo_id().getUId();
        uMoneyInfoDao.updateMoneyByUid(sellerId,money);
        UInfo buyer = uInfoDao.findUInfoByUId(uId);
        uMoneyInfoDao.updateMoneyByUid(uId,money*(-1));

        Subscribe subscribe = new Subscribe();
        subscribe.setCinfo_id(cno);
        subscribe.setStime(new Date());
        subscribe.setUinfo_id(uId);
        subscribeDao.insert(subscribe);
        return 0;
    }
}
