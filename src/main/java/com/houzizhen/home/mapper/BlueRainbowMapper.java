package com.houzizhen.home.mapper;

import com.houzizhen.home.model.BlueRainbow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlueRainbowMapper {
    void insert(BlueRainbow blueRainbow);
}
