package com.houzizhen.home.controller;

import com.github.pagehelper.PageInfo;
import com.houzizhen.home.model.LotteryResult;
import com.houzizhen.home.model.UserLottery;
import com.houzizhen.home.service.LotteryResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/lottery")
@Api(tags = "彩票结果管理", description = "用于处理彩票结果的控制器")
public class LotteryResultController {
    private final LotteryResultService service;

    @Autowired
    public LotteryResultController(LotteryResultService service) {
        this.service = service;
    }

    /**
     * 分页查询所有开奖号码
     *
     * @param page    请求的页码，用于分页展示结果。
     * @param pageSiz 每页显示的结果数量。
     * @return PageInfo<LotteryResult> 包含当前页的彩票结果信息的分页对象。
     */
    @ApiOperation(value = "显示所有彩票结果", notes = "返回'listLotteryResult'视图")
    @GetMapping("/list")
    public PageInfo<LotteryResult> showList(int page, int pageSiz) {
        // 通过服务层方法获取指定页码和页面大小的彩票结果分页信息
        return service.getAllLotteryResults(page, pageSiz);
    }

    @ApiOperation(value = "批量兑奖")
    @PostMapping("/calculate-bonus")
    public Integer calculateBonus(@RequestBody UserLottery userLottery) {
        return service.calculateBonus(userLottery);
    }

    @ApiOperation(value = "随机")
    @PostMapping("/machine-selection")
    public Integer machineSelection(Integer count) {
        return service.machineSelection(count);
    }
}


