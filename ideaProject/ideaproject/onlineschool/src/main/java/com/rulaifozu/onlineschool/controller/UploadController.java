package com.rulaifozu.onlineschool.controller;

import com.rulaifozu.onlineschool.config.ProcessVideoUtil;
import com.rulaifozu.onlineschool.pojo.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin
public class UploadController {
    /**
    * 头像上传
    * */

    @RequestMapping(value = "/headpicupload")
    public Result fileHeadPicUpload(MultipartFile file, HttpServletRequest request) throws Exception, IOException {
        String uploadPath ="C:\\onlineschoolresource\\headpic";
        File folder = new File(uploadPath );
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        // 对上传的文件重命名，避免文件重名
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            // 文件保存
            file.transferTo(new File(folder, newName));
            // 返回上传文件的访问路径
            String filePath = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + "" + request.getContextPath() + "/headpic/"  + newName;
            return Result.ok().put("data",filePath);
        } catch (IOException e) {
            Result.error(e.getMessage());
        }
        return Result.error();
    }

    /**
     * 轮播图上传
     * */

    @RequestMapping(value = "/swiperupload")
    public Result fileSwipeUpload(MultipartFile file, HttpServletRequest request) throws Exception, IOException {
        String uploadPath ="C:\\onlineschoolresource\\swiper";
        File folder = new File(uploadPath );
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        // 对上传的文件重命名，避免文件重名
        String oldName = file.getOriginalFilename();
        String newName = UUID.randomUUID().toString()
                + oldName.substring(oldName.lastIndexOf("."), oldName.length());
        try {
            // 文件保存
            file.transferTo(new File(folder, newName));
            // 返回上传文件的访问路径
            String filePath = request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + "" + request.getContextPath() + "/swiper/"  + newName;
            return Result.ok().put("data",filePath);
        } catch (IOException e) {
            Result.error(e.getMessage());
        }
        return Result.error();
    }

    /**
     * 实现文件上传
     * */
    @RequestMapping(value = "videoUpload",method= RequestMethod.POST)
    /*public String fileUpload(@RequestParam("fileName") MultipartFile file){*/
    public Result fileUpload(HttpServletRequest request){
        /*List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFile("fileName");*/
        MultipartFile file = ((MultipartHttpServletRequest)request).getFile("file");
        if(file.isEmpty()){
            return Result.error("文件为空");
        }
        String filename = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(filename + "-->" + size);

        String path = "C:\\onlineschoolresource\\video\\" ;

        // 获取文件后缀名
        String filename_extension = filename.substring(filename
                .lastIndexOf(".") + 1);
        System.out.println("视频的后缀名:"+filename_extension);

        //时间戳做新的文件名，避免中文乱码-重新生成filename
        long filename1 = new Date().getTime();
        filename = Long.toString(filename1)+"."+filename_extension;

        //去掉后缀的文件名
        String filename2 = filename.substring(0, filename.lastIndexOf("."));
        System.out.println("视频名为:"+filename2);

        //源视频地址+重命名后的视频名+视频后缀
        String yuanPATH =(path+filename);

        System.out.println("视频的完整文件名1:"+filename);
        System.out.println("源视频路径为:"+yuanPATH);
        File dest = new File(path + filename);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }



        try {
            file.transferTo(dest); //保存文件

            //截图
            String imageRealPath= ProcessVideoUtil.processImg(path + filename);
            if(imageRealPath==null){
                System.out.println("截图失败");
            }
            imageRealPath=request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + "" + request.getContextPath() + "/pic/"  +imageRealPath;
            String videoRealPath=request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + "" + request.getContextPath() + "/video/"  +filename;
            Map<String,String> map=new HashMap<String, String>();
            map.put("cvideo",videoRealPath);
            map.put("cpic",imageRealPath);

            return Result.ok().put("data",map);
        } catch (IllegalStateException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }


    /**
     * 实现多文件上传
     * */
    @RequestMapping(value="multivideoUpload",method= RequestMethod.POST)
    /* public @ResponseBody String multifileUpload(@RequestParam("fileName") List<MultipartFile> files) {*/
    public String multifileUpload(HttpServletRequest request){
        //System.out.println(videoService.queryALlVideos());
        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        System.out.println("文件上传开始");
        if(files.size()<=0){
            return "false";
        }

        String path = "C:\\onlineschoolresource\\video\\" ;

        for(MultipartFile file:files){
//            String fileName = Long.toString(System.currentTimeMillis());        //获得当前时间戳，用于给文件重命名
            String filename = file.getOriginalFilename();
            System.out.println("fileName --> "+filename);
            int size = (int) file.getSize();
            System.out.println(filename + "-->" + size);
            // 获取上传时候的文件名

            // 获取文件后缀名
            String filename_extension = filename.substring(filename
                    .lastIndexOf(".") + 1);
            System.out.println("视频的后缀名:"+filename_extension);

            //时间戳做新的文件名，避免中文乱码-重新生成filename
            long filename1 = new Date().getTime();
            filename = Long.toString(filename1)+"."+filename_extension;

            //去掉后缀的文件名
            String filename2 = filename.substring(0, filename.lastIndexOf("."));
            System.out.println("视频名为:"+filename2);

            //源视频地址+重命名后的视频名+视频后缀
            String yuanPATH =(path+filename);

            System.out.println("视频的完整文件名1:"+filename);
            System.out.println("源视频路径为:"+yuanPATH);

            if(file.isEmpty()){
                return "false";
            }else{
                File dest = new File(path + filename);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "false";
                }
            }
            //TODO 视频转码，截图加转储
            //videoService.dumpVideo(path+fileName);

        }
        return "true";
    }
}
