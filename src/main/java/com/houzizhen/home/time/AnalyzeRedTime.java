package com.houzizhen.home.time;

import com.houzizhen.home.mapper.AnalyzeRedMapper;
import com.houzizhen.home.model.AnalyzeRed;
import com.houzizhen.home.model.LotteryResult;
import com.houzizhen.home.service.LotteryResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Slf4j
@Component
public class AnalyzeRedTime {
    @Autowired
    private LotteryResultService lotteryResultService;

    @Autowired
    private AnalyzeRedMapper analyzeRedMapper;

    @Scheduled(cron = "0/10 * * * * ?")
    public void analyzeLotteryResults() {
        analyzeRedMapper.deleteAll();
        List<LotteryResult> allLotteryResults = lotteryResultService.getAllLotteryResults();
        ArrayList<AnalyzeRed> analyzeRedArrayList = new ArrayList<>();
        for (LotteryResult lotteryResult : allLotteryResults) {
            int issue = lotteryResult.getIssue();
            int red1 = lotteryResult.getRed1();
            int red2 = lotteryResult.getRed2();
            int red3 = lotteryResult.getRed3();
            int red4 = lotteryResult.getRed4();
            int red5 = lotteryResult.getRed5();
            int red6 = lotteryResult.getRed6();
            int blue = lotteryResult.getBlue();
            // 计算和
            int sumRed = red1 + red2 + red3 + red4 + red5 + red6;
            AnalyzeRed analyzeRed = new AnalyzeRed();
            analyzeRed.setIssue(issue);
            TreeSet<Integer> treeSet = new TreeSet<>();
            treeSet.add(red1);
            treeSet.add(red2);
            treeSet.add(red3);
            treeSet.add(red4);
            treeSet.add(red5);
            treeSet.add(red6);
            StringBuilder sb = new StringBuilder();
            for (Integer red : treeSet) {
                String format = String.format("%02d", red);
                sb.append(format);
            }
            analyzeRed.setResult(sb.toString());
            analyzeRed.setSum(sumRed);
            analyzeRedArrayList.add(analyzeRed);
        }
        analyzeRedMapper.batchInsert(analyzeRedArrayList);
    }
}
