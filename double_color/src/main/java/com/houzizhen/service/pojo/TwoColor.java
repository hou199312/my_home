package com.houzizhen.service.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class TwoColor {
    @ExcelProperty("id")
    private String id;
    @ExcelProperty("url")
    private String url;
    @ExcelProperty("result")
    private String result;
}
