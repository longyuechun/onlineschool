package com.rulaifozu.onlineschool.controller;

import com.rulaifozu.onlineschool.config.jwtutils.JwtUtils;
import com.rulaifozu.onlineschool.config.jwtutils.Login;
import com.rulaifozu.onlineschool.config.jwtutils.LoginUser;
import com.rulaifozu.onlineschool.pojo.Result;
import com.rulaifozu.onlineschool.pojo.UInfo;
import com.rulaifozu.onlineschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @RequestMapping("login")
    public Result login(@RequestBody Map map){
        String email= (String) map.get("uEmail");
        String password= (String) map.get("uPassword");
        int result=userService.login(email,password);
        if(result>0){
            UInfo uInfo= userService.findUInfoByEmail(email);
            String token= jwtUtils.generateToken(uInfo.getUId());
            Map<String,Object> map1=new HashMap<String, Object>();
            map1.put("token",token);
            map1.put("expire",jwtUtils.getExpire());
            return Result.ok("登录成功").put("data",map1);
        }
        return Result.error("登录失败,账号或密码错误!");
    }
    @Login
    @RequestMapping("finduinfobyuid")
    public Result findUInfoByUId(@LoginUser UInfo uInfo){
        int uId=uInfo.getUId();
        return Result.ok().put("data",userService.findUInfoByUId(uId));
    }
    /*// 判断邮箱是否已经注册过
    @RequestMapping("isregister")
    public Result isRegister (@RequestBody Map map){
        String email= (String) map.get("uEmail");
        //
        int result=userService.isRegister(email);
        if(result==1){
            return Result.ok("可以注册");
        }

        return Result.error("已注册");
    }*/

    //注册
    @RequestMapping("register")
    public Result register(@RequestBody UInfo uInfo){
        String email = uInfo.getUEmail();
        int result=userService.isRegister(email);
        if(result==1){
            userService.register(uInfo);
            return Result.ok("注册成功");
        }
        return Result.error(2,"已注册");
    }
    //
    @RequestMapping("updateuinfo")
    public Result uptateUInfo(@RequestBody UInfo uInfo){
        userService.uptateUInfo(uInfo);
        return Result.ok();
    }




}
