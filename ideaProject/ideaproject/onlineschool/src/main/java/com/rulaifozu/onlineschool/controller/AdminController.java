package com.rulaifozu.onlineschool.controller;

import com.rulaifozu.onlineschool.config.Md5Utils;
import com.rulaifozu.onlineschool.config.jwtutils.JwtUtils;
import com.rulaifozu.onlineschool.config.jwtutils.Login;
import com.rulaifozu.onlineschool.config.jwtutils.LoginAdmin;
import com.rulaifozu.onlineschool.pojo.Ainfo;
import com.rulaifozu.onlineschool.pojo.Result;
import com.rulaifozu.onlineschool.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    JwtUtils jwtUtils;
    @RequestMapping("adminlogin")
    public Result adminLogin(@RequestBody Ainfo aInfo){
        String no=aInfo.getAno();
        String password=aInfo.getApassword();
        int result=adminService.AdminLogin(no,password);
        if(result>0){
            /*int id=Integer.parseInt(aInfo.getAno());
            String token= jwtUtils.generateToken(id);
            Map<String,Object> map1=new HashMap<String, Object>();
            map1.put("token",token);
            map1.put("expire",jwtUtils.getExpire());*/
            return Result.ok("登录成功");
        }
        return Result.error("登录失败,账号或密码错误!");
    }

    @RequestMapping("findainfobyno")
    public Result findAInfoByNo(@RequestBody Map map){
        String no=(String) map.get("ano");
        //System.out.println(ainfo.getAno());
        return Result.ok().put("data",adminService.listByMap(map));
    }


    @RequestMapping("deleteainfobyano")
    public Result deleteAInfoByANo(@RequestBody Map map){
        if(adminService.removeByMap(map)){
            return Result.ok("删除成功");
        }

        return Result.error("删除失败");
    }

    //
    @RequestMapping("updateainfo")
    public Result updateAInfo(@RequestBody Ainfo aInfo){

        if(adminService.saveOrUpdate(aInfo)){
            return Result.ok("更新成功");
        }
        return Result.error("更新失败");
    }
    @RequestMapping("addainfo")
    public Result addAInfo(@RequestBody Ainfo aInfo){
        aInfo.setApassword(Md5Utils.code(aInfo.getApassword()));
        if(adminService.save(aInfo)){
            return Result.ok("添加成功");
        }
        return Result.error("添加失败");
    }
    @RequestMapping("findallainfo")
    public Result findAllAInfo(){
        List<Ainfo> ainfoList=adminService.list();
        if(ainfoList.size()==0){
            return Result.error(100,"没有管理员信息");

        }
        return Result.ok().put("data",ainfoList);
    }
}
