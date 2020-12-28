package com.rulaifozu.onlineschool.controller;

import com.rulaifozu.onlineschool.pojo.Result;
import com.rulaifozu.onlineschool.pojo.Swiper;
import com.rulaifozu.onlineschool.pojo.UInfo;
import com.rulaifozu.onlineschool.service.SwiperService;
import com.rulaifozu.onlineschool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class SwiperController {
    @Autowired
    SwiperService swiperService;

    @RequestMapping("findallswiper")
    public Result findAllSwiper(){
        List<Swiper> swiperList=swiperService.list();
        if(swiperList.size()==0){
            return Result.error(100,"没有数据");
        }
        return Result.ok().put("data",swiperList);
    }

    @RequestMapping("addswiper")
    public Result addSwiper(@RequestBody Swiper swiper){
        if(swiperService.save(swiper)){
            return Result.ok("添加成功");
        }
        return Result.error("添加失败");
    }

    // 删除轮播图
    @RequestMapping("deleteswiperbyid")
    public Result deleteSwiper(@RequestBody Map map){
        int sId= (Integer) map.get("id");
        System.out.println(sId);
        if(swiperService.removeById(sId)){
            return Result.ok("删除成功");
        }
        return Result.error("删除失败");
    }

}
