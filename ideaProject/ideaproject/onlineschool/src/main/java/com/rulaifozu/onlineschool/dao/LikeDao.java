package com.rulaifozu.onlineschool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rulaifozu.onlineschool.pojo.Like;
import com.rulaifozu.onlineschool.pojo.Release;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LikeDao extends BaseMapper<Like> {

    int addLike(Like like);

}
