package com.houzizhen.home.mapper;

import com.houzizhen.home.model.LotteryResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LotteryResultMapper {
    void insertLotteryResult(LotteryResult result);

    LotteryResult getLotteryResultByIssue(@Param("issue") int issue);

    List<LotteryResult> getAllLotteryResults();

    /**
     * 获取最新的一条数据
     *
     * @return 最新一条数据
     */
    LotteryResult getLastLotteryResult();
}
