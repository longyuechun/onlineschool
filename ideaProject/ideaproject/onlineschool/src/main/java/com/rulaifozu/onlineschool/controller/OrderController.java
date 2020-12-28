package com.rulaifozu.onlineschool.controller;


import com.alipay.api.AlipayApiException;
import com.rulaifozu.onlineschool.config.SeedUtil;
import com.rulaifozu.onlineschool.config.jwtutils.Login;
import com.rulaifozu.onlineschool.config.jwtutils.LoginUser;
import com.rulaifozu.onlineschool.pojo.AlipayBean;
import com.rulaifozu.onlineschool.pojo.UInfo;
import com.rulaifozu.onlineschool.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 订单接口
 */
@CrossOrigin
@RestController()
@RequestMapping("order")
public class OrderController {

	@Autowired
	private PayService payService;

	/**
	 * 阿里支付
	 * @return
	 * @throws AlipayApiException
	 */
	@Login
	@PostMapping(value = "alipay/{money}")
	public String alipay(@PathVariable String money,@LoginUser UInfo uInfo) throws AlipayApiException, IOException {
		//String money = (String) map.get("money");
		AlipayBean alipayBean = new AlipayBean();
		alipayBean.setOut_trade_no(SeedUtil.getSeed());
		alipayBean.setSubject("充值"+money+"元");
		alipayBean.setTotal_amount(money);
		//alipayBean.setBody(body);
		return payService.aliPay(alipayBean,uInfo);
	}
}
