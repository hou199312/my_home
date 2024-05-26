package com.houzizhen.home.service;

import com.github.pagehelper.PageInfo;
import com.houzizhen.home.model.LotteryResult;
import com.houzizhen.home.model.UserLottery;

import java.util.List;

public interface LotteryResultService {
    void addLotteryResult(LotteryResult result);

    LotteryResult getLotteryResultByIssue(int issue);

    PageInfo<LotteryResult> getAllLotteryResults(int pageNum, int pageSize);

    List<LotteryResult> getAllLotteryResults();


    /**
     * 计算用户彩票的奖金。
     *
     * @param userLottery 包含用户购买的彩票信息，包括期数和选中的号码。
     * @return 计算出的奖金总额。
     */
    Integer calculateBonus(UserLottery userLottery);

    Integer machineSelection(Integer count);
}
