package com.rulaifozu.onlineschool.service;

import com.alipay.api.AlipayApiException;
import com.rulaifozu.onlineschool.pojo.AlipayBean;
import com.rulaifozu.onlineschool.pojo.UInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 支付服务
 * @author Louis
 * @date Dec 12, 2018
 */
public interface PayService {

	/**
	 * 支付宝支付接口
	 * @param alipayBean
	 * @return
	 * @throws AlipayApiException
	 */
	String aliPay(AlipayBean alipayBean,UInfo uInfo) throws AlipayApiException;

}
