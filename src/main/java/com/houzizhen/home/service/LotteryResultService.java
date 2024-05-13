package com.houzizhen.home.service;

import com.github.pagehelper.PageInfo;
import com.houzizhen.home.model.LotteryResult;

public interface LotteryResultService {
    void addLotteryResult(LotteryResult result);

    void updateLotteryResult(LotteryResult result);

    void deleteLotteryResult(int id);

    LotteryResult getLotteryResultById(int id);

    PageInfo<LotteryResult> getAllLotteryResults(int pageNum, int pageSize);
}
