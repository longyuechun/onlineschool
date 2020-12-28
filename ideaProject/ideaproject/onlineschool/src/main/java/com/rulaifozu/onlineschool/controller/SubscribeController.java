package com.rulaifozu.onlineschool.controller;

import com.rulaifozu.onlineschool.config.Md5Utils;
import com.rulaifozu.onlineschool.config.jwtutils.JwtUtils;
import com.rulaifozu.onlineschool.config.jwtutils.Login;
import com.rulaifozu.onlineschool.config.jwtutils.LoginUser;
import com.rulaifozu.onlineschool.pojo.Ainfo;
import com.rulaifozu.onlineschool.pojo.Result;
import com.rulaifozu.onlineschool.pojo.UInfo;
import com.rulaifozu.onlineschool.service.AdminService;
import com.rulaifozu.onlineschool.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class SubscribeController {

    //TODO 判断有没有订阅
    @Autowired
    SubscribeService subscribeService;

    @Login
    @RequestMapping("isbuy")
    public Result isBuy(@RequestBody Map map,@LoginUser UInfo uInfo){
        String cno = (String) map.get("cno");
        int uId =uInfo.getUId();
        String result=subscribeService.isBuy(cno,uId);
        if(result==null){
            return Result.error(4,"未订阅");
        }else if(result=="yes"){
            return Result.error(6,"这是我发布的");
        }
        return Result.error(5,"已订阅");
    }

    //订阅
    @Login
    @RequestMapping("buycinfo")
    public Result BuyCInfo(@RequestBody Map map,@LoginUser UInfo uInfo){
        String cno = (String) map.get("cno");
        int uId =uInfo.getUId();
        int result = subscribeService.BuyCInfo(cno,uId);
        if(result==0){
            return Result.ok("购买成功");
        }else if(result==1){
            return Result.error(10,"余额不足");
        }
        return Result.error("购买失败");
    }

}
