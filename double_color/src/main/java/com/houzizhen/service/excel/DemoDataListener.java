package com.houzizhen.service.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.houzizhen.service.mapper.LotteryResultMapper;
import com.houzizhen.service.pojo.LotteryResult;
import com.houzizhen.service.pojo.TwoColor;
import com.houzizhen.service.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DemoDataListener implements ReadListener<TwoColor> {

    private static final int BATCH_COUNT = 500;

    private List<LotteryResult> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    LotteryResultMapper lotteryResultMapper;

    public DemoDataListener(LotteryResultMapper lotteryResultMapper) {
        this.lotteryResultMapper = lotteryResultMapper;
    }

    @Override
    public void invoke(TwoColor data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        String s = HttpClientUtils.doGet(data.getUrl(), null);
        int indexOf = s.indexOf("<li class=\"ball_red\">");
        String substring = s.substring(indexOf, indexOf + 300);
        String newStr = substring.replaceAll("\t", "");
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(newStr);
        String all = matcher.replaceAll("");
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < all.length(); i = i + 2) {
            String result = StringUtils.substring(all, i, i + 2);
            integers.add(Integer.parseInt(result));
        }
        LotteryResult lotteryResult = null;
        for (int i = 0; i < 7; i++) {
            lotteryResult = new LotteryResult();
            lotteryResult.setIssue(Integer.parseInt(data.getIssue()));
            lotteryResult.setRed1(integers.get(0));
            lotteryResult.setRed2(integers.get(1));
            lotteryResult.setRed3(integers.get(2));
            lotteryResult.setRed4(integers.get(3));
            lotteryResult.setRed5(integers.get(4));
            lotteryResult.setRed6(integers.get(5));
            lotteryResult.setBlue(integers.get(6));

        }
        cachedDataList.add(lotteryResult);
        if (cachedDataList.size() >= 500) {
            log.info("开始存储数据");
            lotteryResultMapper.insertAll(cachedDataList);
            cachedDataList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        lotteryResultMapper.insertAll(cachedDataList);
        log.info("所有数据解析完成！");
    }
}