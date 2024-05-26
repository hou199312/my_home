package com.houzizhen.home.model;

import lombok.Data;

import java.util.List;

@Data
public class UserLottery {
    private Integer issue;
    private List<LotteryNumber> lotteryNumbers;
}
