package com.rulaifozu.onlineschool.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
//轮播图表
@Data
public class Swiper {
    @TableId(value = "sid",type = IdType.AUTO)//在自增主键的变量加上即可
    private int sid;
    private String spic;//图片url
    private String schain;//链接

}
