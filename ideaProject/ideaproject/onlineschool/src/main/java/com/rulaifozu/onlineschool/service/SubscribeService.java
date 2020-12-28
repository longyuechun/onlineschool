package com.rulaifozu.onlineschool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rulaifozu.onlineschool.pojo.Ainfo;
import com.rulaifozu.onlineschool.pojo.Subscribe;

public interface SubscribeService extends IService<Subscribe> {

    String isBuy(String cno, int uId);

    int BuyCInfo(String cno, int uId);
}
