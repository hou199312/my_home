package com.houzizhen.home.mapper;

import com.houzizhen.home.model.AnalyzeRed;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnalyzeRedMapper {
    void deleteAll();

    void batchInsert(List<AnalyzeRed> analyzeRedList);
}
