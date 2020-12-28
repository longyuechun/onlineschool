package com.rulaifozu.onlineschool.controller;


import com.rulaifozu.onlineschool.config.jwtutils.Login;
import com.rulaifozu.onlineschool.config.jwtutils.LoginUser;
import com.rulaifozu.onlineschool.pojo.CInfo;
import com.rulaifozu.onlineschool.pojo.Result;
import com.rulaifozu.onlineschool.pojo.UInfo;
import com.rulaifozu.onlineschool.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class VideoController  {

    @Autowired
    VideoService videoService;



   /* @RequestMapping("test")
    public List<User> getMess() {
        List<User> users = userService.findAllUser();
        return users;
    }*/

    @RequestMapping("getcinfobycno/{cno}")
    public Result getVideos(@PathVariable String cno) {
        CInfo cInfo = videoService.getCInfoByCNo(cno);
        if(cInfo==null){
            return Result.error("错误");
        }
        return Result.ok().put("data",cInfo);
    }

    //根据课程种类或课程名模糊查询课程信息
    @RequestMapping("findcinfobytypeorcname")
    public Result findCInfoByTypeOrCName(@RequestBody Map map){
        String search= (String) map.get("search");
        System.out.println(search);
        List<CInfo> cInfoList = videoService.findCInfoByTypeOrCName(search);
        if(cInfoList.size()==0){
            return Result.error(100,"没有数据");
        }
        return Result.ok().put("data",cInfoList);
    }

    // 根据类别查询课程信息
    @RequestMapping("findcinfobytype")
    public Result findCInfoByType(@RequestBody Map map){
        String search= (String) map.get("search");
        List<CInfo> cInfoList = videoService.findCInfoByType(search);
        if(cInfoList.size()==0){
            return Result.error(100,"没有数据");
        }
        return Result.ok().put("data",cInfoList);
    }

    // 查询浏览量前四名的课程信息
    @RequestMapping("findcinfobycpageview")
    public Result findCInfoByCPageView(){
        List<CInfo> cInfoList = videoService.findCInfoByCPageView();
        return  Result.ok().put("data",cInfoList);
    }

    // 增加课程
    @Login
    @RequestMapping("addcinfo")
    public Result addCInfo(@RequestBody CInfo cInfo,@LoginUser UInfo uInfo) throws Exception {
        int uId=uInfo.getUId();
        System.out.println(uId);
        if(videoService.addCInfo(cInfo,uId)==0){
            return Result.ok("添加成功");
        }
        return Result.error("添加失败");
    }
    // 查询用户订阅的课程
    @Login
    @RequestMapping("findSubcinfobyuser")
    public Result findSubCInfoBYUser(@LoginUser UInfo uInfo){
        int uId=uInfo.getUId();
        List<CInfo> cInfoList = videoService.findSubCInfoBYUser(uId);
        if(cInfoList.size()==0){
            return Result.error(100,"没有数据");
        }
        return Result.ok().put("data",cInfoList);
    }
    // 查询用户发布的课程
    @Login
    @RequestMapping("findrecinfobyuser")
    public Result findReCInfoBYUser(@LoginUser UInfo uInfo){
        int uId=uInfo.getUId();
        List<CInfo> cInfoList = videoService.findReCInfoBYUser(uId);
        if(cInfoList.size()==0){
            return Result.error(100,"没有数据");
        }
        return Result.ok().put("data",cInfoList);
    }
    //查询用户未通过审核的课程
    @Login
    @RequestMapping("findrecinfobyuserstatus0")
    public Result findReCInfoBYUserStatus0(@LoginUser UInfo uInfo){
        int uId=uInfo.getUId();
        List<CInfo> cInfoList = videoService.findReCInfoBYUserStatus0(uId);
        if(cInfoList.size()==0){
            return Result.error(100,"没有数据");
        }
        return Result.ok().put("data",cInfoList);
    }
    //查询用户正在审核的课程
    @Login
    @RequestMapping("findrecinfobyuserstatus2")
    public Result findReCInfoBYUserStatus2(@LoginUser UInfo uInfo){
        int uId=uInfo.getUId();
        List<CInfo> cInfoList = videoService.findReCInfoBYUserStatus2(uId);
        if(cInfoList.size()==0){
            return Result.error(100,"没有数据");
        }
        return Result.ok().put("data",cInfoList);
    }
    // 查询用户已发布的课程
    @Login
    @RequestMapping("findrecinfobyuserstatus1")
    public Result findReCInfoBYUserStatus1(@LoginUser UInfo uInfo){
        int uId=uInfo.getUId();
        List<CInfo> cInfoList = videoService.findReCInfoBYUserStatus1(uId);
        if(cInfoList.size()==0){
            return Result.error(100,"没有数据");
        }
        return Result.ok().put("data",cInfoList);
    }
    // 查询所有正在审核的课程
    @RequestMapping("findcinfobystatus2")
    public Result findCInfoBYStatus2(){
        List<CInfo> cInfoList = videoService.findCInfoBYStatus2();
        if(cInfoList.size()==0){
            return Result.error(100,"没有数据");
        }
        return Result.ok().put("data",cInfoList);
    }
    // 在类别中模糊搜素课程
    @RequestMapping("findcinfobynameintype")
    public Result findCInfoByNameInType(@RequestBody Map<String,Object> map){
        String search= (String) map.get("search");
        String type = (String) map.get("type");
        List<CInfo> cInfoList = videoService.findCInfoByNameInType(search,type);
        if(cInfoList.size()==0){
            return Result.error(100,"没有数据");
        }
        return Result.ok().put("data",cInfoList);
    }
    // 修改课程状态
    @RequestMapping("updatecinfostatus")
    public Result updateCInfoStatus(@RequestBody Map<String,Object> map){
        videoService.updateCInfoStatus(map);
        return Result.ok();
    }



    //TODO 根据cno删除课程
    @RequestMapping("deletecinfobycno")
    public Result deleteCInfoByCNo(@RequestBody Map map){
        String cno= (String) map.get("cno");
        if(videoService.deleteCInfoByCNo(cno)==0){

        }
        return Result.error("删除失败");
    }

    // 点赞
    @Login
    @RequestMapping("like")
    public Result like(@RequestBody Map map, @LoginUser UInfo uInfo) throws ParseException {
        String cno = (String) map.get("cno");
        int uid=uInfo.getUId();
        videoService.like(cno,uInfo);
        return Result.ok();
    }
    // 点击
    @RequestMapping("addcpageview")
    public Result addCPageView(@RequestBody Map map){
        String cno = (String) map.get("cno");
        videoService.addCPageView(cno);
        return Result.ok();
    }
    //TODO 评论
    @Login
    @RequestMapping("newmessage")
    public Result newMessage(@LoginUser UInfo uInfo){

        return Result.ok();
    }



   /* @RequestMapping("getVideoList")
    public Result getVideoList(){
        List<CInfo> videos = videoService.queryALlVideos();
        if(videos!=null){
            return Result.ok().put("data", videos);
        }else{
            return Result.error();
        }
    }*/

}
