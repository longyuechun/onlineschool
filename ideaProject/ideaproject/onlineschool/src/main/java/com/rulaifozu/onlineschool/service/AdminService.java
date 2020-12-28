package com.rulaifozu.onlineschool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rulaifozu.onlineschool.pojo.Ainfo;

public interface AdminService extends IService<Ainfo> {
    public int AdminLogin (String no,String pword);

}
