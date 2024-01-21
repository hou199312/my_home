package com.houzizhen.service.utils;

import com.houzizhen.service.mapper.RedRainbowDisorderMapper;
import com.houzizhen.service.pojo.RedRainbowDisorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

public class MyUtils {

    public static List<RedRainbowDisorder> init() {
        ArrayList<RedRainbowDisorder> list = new ArrayList<>();
        int num = 0;
        for (int i = 1; i < 29; i++) {
            for (int j = i + 1; j < 30; j++) {
                for (int k = j + 1; k < 31; k++) {
                    for (int l = k + 1; l < 32; l++) {
                        for (int m = l + 1; m < 33; m++) {
                            for (int n = m + 1; n < 34; n++) {
                                num++;
                                RedRainbowDisorder redRainbowDisorder = new RedRainbowDisorder();
                                redRainbowDisorder.setRed1(i);
                                redRainbowDisorder.setRed2(j);
                                redRainbowDisorder.setRed3(k);
                                redRainbowDisorder.setRed4(l);
                                redRainbowDisorder.setRed5(m);
                                redRainbowDisorder.setRed6(n);
                                list.add(redRainbowDisorder);
                            }
                        }
                    }
                }
            }
        }
        return list;
    }


}
