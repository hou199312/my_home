package com.houzizhen.home.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.houzizhen.home.mapper.LotteryResultMapper;
import com.houzizhen.home.model.LotteryNumber;
import com.houzizhen.home.model.LotteryResult;
import com.houzizhen.home.model.UserLottery;
import com.houzizhen.home.service.LotteryResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class LotteryResultServiceImpl implements LotteryResultService {
    private final LotteryResultMapper mapper;
    private final LotteryResultMapper lotteryResultMapper;

    @Autowired
    public LotteryResultServiceImpl(LotteryResultMapper mapper, LotteryResultMapper lotteryResultMapper) {
        this.mapper = mapper;
        this.lotteryResultMapper = lotteryResultMapper;
    }

    @Override
    public void addLotteryResult(LotteryResult result) {
        mapper.insertLotteryResult(result);
    }


    @Override
    public LotteryResult getLotteryResultByIssue(int id) {
        return mapper.getLotteryResultByIssue(id);
    }

    @Override
    public PageInfo<LotteryResult> getAllLotteryResults(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<LotteryResult> allLotteryResults = mapper.getAllLotteryResults();
        return new PageInfo<>(allLotteryResults);
    }

    @Override
    public List<LotteryResult> getAllLotteryResults() {
        return mapper.getAllLotteryResults();
    }

    /**
     * 计算用户彩票的奖金。
     *
     * @param userLottery 包含用户购买的彩票信息，包括期数和选中的号码。
     * @return 计算出的奖金总额。
     */
    @Override
    public Integer calculateBonus(UserLottery userLottery) {
        int bonus = 0; // 初始化奖金为0
        Integer issue = userLottery.getIssue(); // 获取用户购买的彩票期数
        // 根据期数查询彩票结果
        LotteryResult lotteryResultByIssue = mapper.getLotteryResultByIssue(issue);
        // 创建集合存储中奖号码
        HashSet<Integer> lotteryNumber = new HashSet<>();
        // 添加中奖的红色号码到集合
        lotteryNumber.add(lotteryResultByIssue.getRed1());
        lotteryNumber.add(lotteryResultByIssue.getRed2());
        lotteryNumber.add(lotteryResultByIssue.getRed3());
        lotteryNumber.add(lotteryResultByIssue.getRed4());
        lotteryNumber.add(lotteryResultByIssue.getRed5());
        lotteryNumber.add(lotteryResultByIssue.getRed6());

        // 获取用户购买的所有号码
        List<LotteryNumber> userNumbers = userLottery.getLotteryNumbers();
        // 遍历用户购买的所有号码
        for (LotteryNumber userNumber : userNumbers) {
            // 创建集合存储用户选中的红色号码
            HashSet<Integer> user = new HashSet<>();
            // 添加用户选中的红色号码到集合
            user.add(userNumber.getRed1());
            user.add(userNumber.getRed2());
            user.add(userNumber.getRed3());
            user.add(userNumber.getRed4());
            user.add(userNumber.getRed5());
            user.add(userNumber.getRed6());
            // 取用户选中号码和中奖号码的交集，即匹配的红色号码数量
            user.retainAll(lotteryNumber);
            int size = user.size(); // 匹配的红色号码数量
            // 检查是否匹配蓝色号码
            if (lotteryResultByIssue.getBlue() == userNumber.getBlue()) {
                // 根据匹配的红色号码数量和蓝色号码，计算奖金
                switch (size) {
                    case 6:
                        bonus += 5000000;
                        break;
                    case 5:
                        bonus += 3000;
                        break;
                    case 4:
                        bonus += 200;
                        break;
                    case 3:
                        bonus += 10;
                        break;
                    case 2:
                    case 1:
                    case 0:
                        bonus += 5;
                        break;
                }
            } else {
                // 如果没有匹配蓝色号码，根据匹配的红色号码数量计算奖金
                switch (size) {
                    case 6:
                        bonus += 200000;
                        break;
                    case 5:
                        bonus += 200;
                        break;
                    case 4:
                        bonus += 10;
                        break;
                    case 3:
                    case 2:
                    case 1:
                    case 0:
                        bonus += 0;
                        break;
                }
            }
        }
        return bonus; // 返回计算出的总奖金
    }

    @Override
    public Integer machineSelection(Integer count) {
        Random random = new Random();
        int i = random.nextInt(1107568) + 1;
        return 0;
    }

    @Override
    public LotteryResult getLastLotteryResult() {
       return lotteryResultMapper.getLastLotteryResult();
    }

}

