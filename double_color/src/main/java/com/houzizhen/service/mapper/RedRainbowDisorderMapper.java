package com.houzizhen.service.mapper;

import com.houzizhen.service.pojo.RedRainbowDisorder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RedRainbowDisorderMapper {
    void insertAll(@Param("list") List<RedRainbowDisorder> redRainbowDisorderList);
}
