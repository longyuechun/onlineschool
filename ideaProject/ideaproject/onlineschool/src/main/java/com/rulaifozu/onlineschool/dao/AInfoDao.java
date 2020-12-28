package com.rulaifozu.onlineschool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rulaifozu.onlineschool.pojo.Ainfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AInfoDao extends BaseMapper<Ainfo> {
    public String findAPassWordByNo(String no);

}
