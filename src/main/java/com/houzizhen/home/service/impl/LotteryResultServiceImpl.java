package com.houzizhen.home.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.houzizhen.home.mapper.LotteryResultMapper;
import com.houzizhen.home.model.LotteryResult;
import com.houzizhen.home.service.LotteryResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class LotteryResultServiceImpl implements LotteryResultService {
    private final LotteryResultMapper mapper;

    @Autowired
    public LotteryResultServiceImpl(LotteryResultMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void addLotteryResult(LotteryResult result) {
        mapper.insertLotteryResult(result);
    }

    @Override
    public void updateLotteryResult(LotteryResult result) {
        mapper.updateLotteryResult(result);
    }

    @Override
    public void deleteLotteryResult(int id) {
        mapper.deleteLotteryResult(id);
    }

    @Override
    public LotteryResult getLotteryResultById(int id) {
        return mapper.getLotteryResultById(id);
    }

    @Override
    public PageInfo<LotteryResult> getAllLotteryResults(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<LotteryResult> allLotteryResults = mapper.getAllLotteryResults();
        return new PageInfo<>(allLotteryResults);
    }

}

