package com.rulaifozu.onlineschool.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
//admin信息表
@Data
public class Ainfo {
    @TableId(value = "ano")
    private String ano;
    private String apassword;
    private String agrade;//等级
    private String aphonenum;
    private String asex;
    private String aemail;
    private int aage;
}
