package com.rulaifozu.onlineschool.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rulaifozu.onlineschool.config.Md5Utils;
import com.rulaifozu.onlineschool.dao.AInfoDao;
import com.rulaifozu.onlineschool.pojo.Ainfo;
import com.rulaifozu.onlineschool.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends ServiceImpl<AInfoDao, Ainfo> implements AdminService {
	@Autowired
	AInfoDao aInfoDao;


	@Override
	public int AdminLogin(String no, String pword) {
		String password=aInfoDao.findAPassWordByNo(no);
		pword= Md5Utils.code(pword);
		if(password.equals(pword)){
			return 1;
		}
		return 0;
	}
}
