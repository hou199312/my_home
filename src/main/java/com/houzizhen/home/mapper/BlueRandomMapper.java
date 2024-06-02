package com.houzizhen.home.mapper;

import com.houzizhen.home.model.BlueRandom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlueRandomMapper {
    // 获取数据量
    int getCount();

    List<BlueRandom> selectAll();

}
