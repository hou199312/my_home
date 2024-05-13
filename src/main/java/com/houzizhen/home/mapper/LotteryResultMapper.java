package com.houzizhen.home.mapper;

import com.houzizhen.home.model.LotteryResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LotteryResultMapper {
    void insertLotteryResult(LotteryResult result);
    void updateLotteryResult(LotteryResult result);
    void deleteLotteryResult(int id);
    LotteryResult getLotteryResultById(int id);
    List<LotteryResult> getAllLotteryResults();
}
