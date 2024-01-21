package com.houzizhen.service.mapper;

import com.houzizhen.service.pojo.LotteryResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LotteryResultMapper {
    void insertAll(@Param("list") List<LotteryResult> lotteryResultList);
}
