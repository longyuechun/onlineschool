package com.rulaifozu.onlineschool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rulaifozu.onlineschool.pojo.Ainfo;
import com.rulaifozu.onlineschool.pojo.Subscribe;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SubscribeDao extends BaseMapper<Subscribe> {
    public String findAPassWordByNo(String no);

    String findCnoByCnoUId(String cno, int uId);

    int addSub(Subscribe subscribe);
}
