package com.rulaifozu.onlineschool.service.impl;

import com.alipay.api.AlipayApiException;
import com.rulaifozu.onlineschool.alipay.Alipay;
import com.rulaifozu.onlineschool.config.Md5Utils;
import com.rulaifozu.onlineschool.dao.UInfoDao;
import com.rulaifozu.onlineschool.dao.UMoneyInfoDao;
import com.rulaifozu.onlineschool.pojo.AlipayBean;
import com.rulaifozu.onlineschool.pojo.UInfo;
import com.rulaifozu.onlineschool.pojo.UMoneyInfo;
import com.rulaifozu.onlineschool.service.PayService;
import com.rulaifozu.onlineschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UInfoDao uInfoDao;
	@Autowired
	UMoneyInfoDao uMoneyInfoDao;
	@Override
	public int login(String uEmail, String pword) {
		String password=uInfoDao.findPassWordByUEmail(uEmail);
		System.out.println(password);
		System.out.println(pword);
		System.out.println(uEmail);
		pword= Md5Utils.code(pword);
		if(password.equals(pword)){

			return 1;
		}
		return 0;
	}

	@Override
	public UInfo findUInfoByUId(int uId) {
		return uInfoDao.findUInfoByUId(uId);

	}
	@Transactional
	@Override
	public int register(UInfo uInfo) {
		UMoneyInfo uMoneyInfo=new UMoneyInfo();
		uMoneyInfo.setUMoney(0);
		uInfo.setUAge(18);
		uInfo.setUSex("男");
		uInfo.setUPic("");//todo
		uInfo.setUMoneyInfo_id(uMoneyInfo);
		uInfo.setUPassword(Md5Utils.code(uInfo.getUPassword()));
		uMoneyInfoDao.addUMoneyInfo(uMoneyInfo);
		uInfoDao.addUInfo(uInfo);
		return 0;
	}

	@Override
	public UInfo findUInfoByEmail(String email) {
		return uInfoDao.findUInfoByEmail(email);
	}

	@Override
	public int isRegister(String email) {
		String id=uInfoDao.findIdByEmail(email);
		if(id==null){
			return 1;
		}
		return 0;
	}

	@Override
	public int uptateUInfo(UInfo uInfo) {
		return uInfoDao.uptateUInfo(uInfo);
	}


}
