package com.rulaifozu.onlineschool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rulaifozu.onlineschool.pojo.Swiper;
import com.rulaifozu.onlineschool.pojo.UInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface SwiperDao extends BaseMapper<Swiper> {
}
