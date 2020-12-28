package com.rulaifozu.onlineschool.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProcessVideoUtil {
    public static final String ffmpegpath = "C:\\ffmpeg\\bin\\ffmpeg.exe";		// ffmpeg工具安装位置
    public static final String imageRealPath = "C:\\onlineschoolresource\\pic\\"; // 截图的存放目录

    private static String filerealname;				 			//文件名不包括后缀名
    private static String filename; 								//包括后缀名
    public ProcessVideoUtil(){

    }
    /**
     * 视频截图功能
     * @param sourceVideoPath 需要被截图的视频路径（包含文件名和后缀名）
     * @return
     */
    public static String processImg(String sourceVideoPath) {

        //先确保保存截图的文件夹存在
        File TempFile = new File(imageRealPath);
        if (TempFile.exists()) {
            if (TempFile.isDirectory()) {
                System.out.println("该文件夹存在。");
            }else {
                System.out.println("同名的文件存在，不能创建文件夹。");
            }
        }else {
            System.out.println("文件夹不存在，创建该文件夹。");
            TempFile.mkdir();
        }

        File fi = new File(sourceVideoPath);
        filename = fi.getName();			//获取视频文件的名称。
        filerealname = filename.substring(0, filename.lastIndexOf("."));	//获取视频名+不加后缀名 后面加.toLowerCase()转为小写

        List<String> commend = new ArrayList<String>();
        //第一帧： 00:00:01
        //截图命令：time ffmpeg -ss 00:00:01 -i test1.flv -f image2 -y test1.jpg

        commend.add(ffmpegpath);			//指定ffmpeg工具的路径
        commend.add("-ss");
        commend.add("00:00:01");			//1是代表第1秒的时候截图
        commend.add("-i");
        commend.add(sourceVideoPath);		//截图的视频路径
        commend.add("-f");
        commend.add("image2");
        commend.add("-y");
        commend.add(imageRealPath + filerealname + ".jpg");		//生成截图xxx.jpg

        //打印截图命令--zoutao
        StringBuffer test = new StringBuffer();
        for (int i = 0; i < commend.size(); i++) {
            test.append(commend.get(i) + " ");
        }
        System.out.println("截图命令:"+test);

        //转码后完成截图功能-还是得用线程来解决--zoutao
        try {
			/*ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			Process p =builder.start();*/
            //调用线程处理命令
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            Process p = builder.start();

            //获取进程的标准输入流
            final InputStream is1 = p.getInputStream();
            //获取进程的错误流
            final InputStream is2 = p.getErrorStream();
            //启动两个线程，一个线程负责读标准输出流，另一个负责读标准错误流
            new Thread() {
                public void run() {
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(is1));
                    try {
                        String lineB = null;
                        while ((lineB = br.readLine()) != null) {
                            if (lineB != null){
                                //System.out.println(lineB);    //必须取走线程信息避免堵塞
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //关闭流
                    finally{
                        try {
                            is1.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }.start();
            new Thread() {
                public void run() {
                    BufferedReader br2 = new BufferedReader(
                            new InputStreamReader(is2));
                    try {
                        String lineC = null;
                        while ((lineC = br2.readLine()) != null) {
                            if (lineC != null)   {
                                //System.out.println(lineC);   //必须取走线程信息避免堵塞
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //关闭流
                    finally{
                        try {
                            is2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }.start();
            // 等Mencoder进程转换结束，再调用ffmepg进程非常重要！！！
            p.waitFor();
            System.out.println("截图进程结束");
            return  filerealname + ".jpg";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
