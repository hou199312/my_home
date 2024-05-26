package com.houzizhen.home.time;

import com.houzizhen.home.mapper.AnalyzeBlueMapper;
import com.houzizhen.home.mapper.BlueRainbowMapper;
import com.houzizhen.home.model.AnalyzeBlue;
import com.houzizhen.home.model.BlueRainbow;
import com.houzizhen.home.model.LotteryResult;
import com.houzizhen.home.service.LotteryResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class AnalyzeBlueTime {
    @Autowired
    private LotteryResultService lotteryResultService;

    @Autowired
    private AnalyzeBlueMapper analyzeBlueMapper;

    @Autowired
    private BlueRainbowMapper blueRainbowMapper;

    @Scheduled(cron = "0/10 * * * * ?")
    public void analyzeLotteryResults() {
        analyzeBlueMapper.deleteAll();
        List<LotteryResult> allLotteryResults = lotteryResultService.getAllLotteryResults();
        ArrayList<AnalyzeBlue> analyzeBlueArrayList = new ArrayList<>();
        for (LotteryResult lotteryResult : allLotteryResults) {
            int issue = lotteryResult.getIssue();
            int blue = lotteryResult.getBlue();
            String format = String.format("%02d", blue);

            AnalyzeBlue analyzeBlue = new AnalyzeBlue();
            analyzeBlue.setResult(format);
            analyzeBlue.setIssue(issue);
            analyzeBlueArrayList.add(analyzeBlue);
        }
        analyzeBlueMapper.batchInsert(analyzeBlueArrayList);
    }


    @Scheduled(cron = "50 42 * * * ?")
    public void blue() {
        for (int i = 1; i <= 16; i++) {
            for (int j = 1; j <= 16; j++) {
                for (int k = 1; k <= 16; k++) {
                    BlueRainbow blueRainbow = new BlueRainbow();
                    blueRainbow.setBlue1(i);
                    blueRainbow.setBlue2(j);
                    blueRainbow.setBlue3(k);
                    blueRainbowMapper.insert(blueRainbow);
                }
            }
        }
    }
}
