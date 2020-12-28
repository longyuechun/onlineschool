package com.rulaifozu.onlineschool.pojo;

import lombok.Data;

import java.util.Date;
//用户订单表
@Data
public class UOrder {
    private String oId;
    private int oMoney;
    private Date oTime;
    private int oType;//订单类型 1充值 2消费
    private UInfo uInfo_id;
}
