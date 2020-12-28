package com.rulaifozu.onlineschool.dao;

import com.rulaifozu.onlineschool.pojo.CInfo;
import com.rulaifozu.onlineschool.pojo.Release;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReleaseDao {
    Release findReleaseById();

    int addRelease(Release release);
}
