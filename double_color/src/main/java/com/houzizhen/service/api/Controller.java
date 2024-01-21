package com.houzizhen.service.api;

import com.alibaba.excel.EasyExcel;
import com.houzizhen.service.excel.DemoDataListener;
import com.houzizhen.service.mapper.RedRainbowDisorderMapper;
import com.houzizhen.service.pojo.RedRainbowDisorder;
import com.houzizhen.service.pojo.TwoColor;
import com.houzizhen.service.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/service")
public class Controller {
    @Autowired
    RedRainbowDisorderMapper redRainbowDisorderMapper;

    @GetMapping("/get")
    public void getNumber() {

        ArrayList<RedRainbowDisorder> list = new ArrayList<>();
        for (int i = 1; i < 29; i++) {
            for (int j = i + 1; j < 30; j++) {
                for (int k = j + 1; k < 31; k++) {
                    for (int l = k + 1; l < 32; l++) {
                        for (int m = l + 1; m < 33; m++) {
                            for (int n = m + 1; n < 34; n++) {
                                RedRainbowDisorder redRainbowDisorder = new RedRainbowDisorder();
                                redRainbowDisorder.setRed1(i);
                                redRainbowDisorder.setRed2(j);
                                redRainbowDisorder.setRed3(k);
                                redRainbowDisorder.setRed4(l);
                                redRainbowDisorder.setRed5(m);
                                redRainbowDisorder.setRed6(n);
                                list.add(redRainbowDisorder);
                                if (list.size() == 500) {
                                    redRainbowDisorderMapper.insertAll(list);
                                    list.clear();
                                }
                            }
                        }
                    }
                }
            }
        }
        redRainbowDisorderMapper.insertAll(list);


//        String fileName = "E:\\500w.xlsx";
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, TwoColor.class, new DemoDataListener()).sheet().doRead();

//        for (int i = 0; i < 100; i++) {
//            int begin = 3002;
//            String format = String.format("%0" + 5 + "d", begin);
//            String url = "http://kaijiang.500.com/shtml/ssq/" + format + ".shtml";
//            System.out.println(url);
//            String s = HttpClientUtils.doGet(url, null);
//            int indexOf = s.indexOf("<li class=\"ball_red\">");
//            String substring = s.substring(indexOf, indexOf + 300);
//            String newStr = substring.replaceAll("\t", "");
//            Pattern pattern = Pattern.compile("[^0-9]");
//            Matcher matcher = pattern.matcher(newStr);
//            String all = matcher.replaceAll("");
//            System.out.println("phone:" + all);
//            // 2
//            Pattern.compile("[^0-9]").matcher(newStr).replaceAll("");
//        }
    }

    @GetMapping("two-color")
    public String getTwoColor() {
        int[] luckNumber = createLuckNumber();
        Arrays.sort(luckNumber);
        String string = Arrays.toString(luckNumber);
        System.out.println(string);
        boolean equals = "[4, 5, 6, 7, 20, 22, 15]".equals(string);
        if (!equals) {
            getTwoColor();
        }
        return Arrays.toString(createLuckNumber());
    }

    public static int[] createLuckNumber() {
//        a.定义一个动态初始化的数组，存储七个数字
        int[] numers = new int[7];
//        b.遍历数组，为每个位置生成对应的号码(注意 ， 遍历前六个位置，生成六个不重复的红球号码，范围是（1-33）
        Random r = new Random();
        for (int i = 0; i < numers.length; i++) {
            while (true) {
                int data = r.nextInt(33) + 1;
//      c、注意，必须判断当前随机的号码有没有重复，若重复，则必须要重新随机一个号码 直到不重复为止，才可以存到数组中去

//                定义一个flag变量，默认data没有重复
                boolean flag = true;
                for (int j = 0; j < i; j++) {
                    if (numers[j] == data) {
//                        data这个数据之前出现过，不能用
                        flag = false;
                        break;
                    }
                }
                if (flag) {
//                    说明data这个数据之前没有出现过 可以使用 并并把它装到数组中去
                    numers[i] = data;
                    break;
                }
            }
        }
//        d. 为第七个位置生成一个1-16的号码作为蓝球号码
        numers[numers.length - 1] = r.nextInt(16) + 1;
        return numers;
    }

    public static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i == arr.length - 1 ? arr[i] : arr[i] + ",");
        }
        System.out.println("]");
    }


    public static Integer judge(int[] luckyNumbers, int[] userNumbers) {
        Integer num;
//        判断是否中奖了
//        1、定义两个变量分别存储红球命中个数与篮球命中个数。
        int redHitNumbers = 0;
        int blueHitNumbers = 0;

//        2、判断红球命中个数，开始统计
        for (int i = 0; i < userNumbers.length - 1; i++) {
            for (int j = 0; j < luckyNumbers.length - 1; j++) {
//                每次找到相等的就意味着当前号码命中了
                if (userNumbers[i] == luckyNumbers[j]) {
                    redHitNumbers++;
                    break;
                }
            }
        }
//        判断篮球号码是否命中
        blueHitNumbers = luckyNumbers[luckyNumbers.length - 1] == userNumbers[userNumbers.length - 1] ? 1 : 0;
//        判断中奖情况
        if (blueHitNumbers == 1 && redHitNumbers < 3) {
            num = 5;

        } else if (blueHitNumbers == 1 && redHitNumbers == 3 || blueHitNumbers == 0 && redHitNumbers == 4) {
            num = 10;

        } else if (blueHitNumbers == 1 && redHitNumbers == 4 || blueHitNumbers == 0 && redHitNumbers == 5) {
            num = 200;

        } else if (blueHitNumbers == 1 && redHitNumbers == 5) {
            num = 3000;

        } else if (blueHitNumbers == 0 && redHitNumbers == 6) {
            num = 200000;

        } else if (blueHitNumbers == 1 && redHitNumbers == 6) {
            num = 5000000;

        } else {
            num = 0;
        }
        return num;
    }
}