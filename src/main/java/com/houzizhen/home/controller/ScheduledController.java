package com.houzizhen.home.controller;

import com.houzizhen.home.time.CrawlDataTime;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduled")
@Api(tags = "彩票结果管理", description = "手动触发定时任务")
public class ScheduledController {
    @Autowired
    private CrawlDataTime crawlDataTime;

    @ApiOperation(value = "手动触发爬取数据")
    @GetMapping("/getLotteryResult")
    public void getLotteryResult() throws Exception {
        crawlDataTime.getLotteryResult();
    }
}
