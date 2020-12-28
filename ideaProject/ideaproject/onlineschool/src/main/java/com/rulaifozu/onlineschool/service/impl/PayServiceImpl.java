package com.rulaifozu.onlineschool.service.impl;

import com.alipay.api.AlipayApiException;
import com.rulaifozu.onlineschool.alipay.Alipay;
import com.rulaifozu.onlineschool.dao.OrderDao;
import com.rulaifozu.onlineschool.dao.UMoneyInfoDao;
import com.rulaifozu.onlineschool.pojo.AlipayBean;
import com.rulaifozu.onlineschool.pojo.UInfo;
import com.rulaifozu.onlineschool.pojo.UMoneyInfo;
import com.rulaifozu.onlineschool.pojo.UOrder;
import com.rulaifozu.onlineschool.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Service
public class PayServiceImpl implements PayService {

	@Autowired
	private Alipay alipay;

	@Autowired
	OrderDao orderDao;
	@Autowired
	UMoneyInfoDao uMoneyInfoDao;
	@Transactional
	@Override
	public String aliPay(AlipayBean alipayBean,UInfo uInfo) throws AlipayApiException {
		String result = alipay.pay(alipayBean);
		UOrder uOrder = new UOrder();
		uOrder.setUInfo_id(uInfo);
		uOrder.setOId(alipayBean.getOut_trade_no());
		uOrder.setOMoney(Integer.parseInt(alipayBean.getTotal_amount()));
		uOrder.setOTime(new Date());
		uOrder.setOType(1);
		orderDao.addUOrder(uOrder);
		uMoneyInfoDao.updateMoneyByUid(uInfo.getUId(),uOrder.getOMoney()*10);
		return result;
	}

}
