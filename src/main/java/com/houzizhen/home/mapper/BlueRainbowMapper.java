package com.houzizhen.home.mapper;

import com.houzizhen.home.model.BlueRainbow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BlueRainbowMapper {
    void insert(BlueRainbow blueRainbow);


}
