package com.rulaifozu.onlineschool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rulaifozu.onlineschool.pojo.UOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderDao extends BaseMapper<UOrder> {
    int addUOrder(UOrder uorder);

}
