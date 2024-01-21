package com.houzizhen.service.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class TwoColor {
    @ExcelProperty("issue")
    private String issue;
    @ExcelProperty("url")
    private String url;
    @ExcelProperty("开奖结果")
    private String result;
}
