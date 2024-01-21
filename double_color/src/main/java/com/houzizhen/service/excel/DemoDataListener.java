package com.houzizhen.service.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.houzizhen.service.pojo.TwoColor;
import com.houzizhen.service.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DemoDataListener implements ReadListener<TwoColor> {

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<TwoColor> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);


    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(TwoColor data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        saveData(data);

    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        simpleFill(cachedDataList);
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData(TwoColor data) {
        log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        String s = HttpClientUtils.doGet(data.getUrl(), null);
        int indexOf = s.indexOf("����˳��");
        String substring = s.substring(indexOf, indexOf + 100);
        String newStr = substring.replaceAll("\t", "");
        Pattern pattern = Pattern.compile("[^0-9]");
        Matcher matcher = pattern.matcher(newStr);
        String all = matcher.replaceAll("");
        TwoColor twoColor = new TwoColor();
        twoColor.setId(data.getId());
        twoColor.setUrl(data.getUrl());
        twoColor.setResult(all);
        cachedDataList.add(twoColor);
    }

    public void simpleFill(List<TwoColor> twoColorList) {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        // 填充list 的时候还要注意 模板中{.} 多了个点 表示list
        // 如果填充list的对象是map,必须包涵所有list的key,哪怕数据为null，必须使用map.put(key,null)
        String fileName = "E:\\500w2.xlsx";
        EasyExcel.write(fileName, TwoColor.class).sheet("模板").doWrite(twoColorList);


    }
}