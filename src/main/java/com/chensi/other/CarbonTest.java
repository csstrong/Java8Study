package com.chensi.other;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
 * @author  chensi
 * @date  2022/8/1
 */
public class CarbonTest {
    /**
     * 低碳逆推
     * 1.按照碳排放量建立一元二次方程组方程格式为Y=a(x-b)^2+c,其中b（年份）,c（吨）为1输入的已知量
     * 2.得到碳排放量之后，利用标准煤和碳排放之间的换算因子可以到当前年份的标准煤消耗量。(标煤的碳排放因子为：2.66-2.72吨CO2/吨标准煤)
     * 3.对标准煤消耗量进行分解，按照前几年的数据进行拆解得到各个能源换算成标准煤后的权重，进而得到中间预测年份各个能源的消耗情况。权重的具体计算方式如下：
     * 煤炭：-0.1*x*x + 401.3*x - 402600
     * 原油：0.04674*x*x-188*x+189000
     * 天然气：0.01424*x*x-56.92*x + 56870
     *
     * @param targetYear
     * @param targetCarbon
     * @return
     */
    @ParameterizedTest
    @CsvSource({
        "2030, 100000"
    })
    public void getCarbonDistributeInfo(String targetYear, String targetCarbon) {

        ImmutableMap<Object, Object> map = ImmutableMap.builder().put("2010", "5600").put("2011", "6600").put("2012", "8600")
            .put("2013", "12500").put("2014", "16000").put("2015", "20000").put("2016", "23000").put("2017", "28000")
            .put("2018", "33000").put("2019", "40000").put("2020", "50000").put("2021", "60000").put("2022", "70000").build();
        try {
            LocalDateTime now = LocalDateTime.now();
            int nowYear = now.getYear();
            int b = Integer.parseInt(targetYear);
            double c = Double.parseDouble(targetCarbon);

            JSONObject resJson = new JSONObject();
            int mid = map.size() / 2;
            String yearKey = null;
            double value = 0d;
            ImmutableList<Object> keyList = map.keySet().asList();
            for (int i = 0; i < keyList.size(); i++) {
                if (i == mid) {
                    yearKey = keyList.get(i).toString();
                    value = Double.parseDouble(map.get(yearKey).toString());
                    break;
                }
            }

            double a = (value - c) / (Math.pow((Integer.parseInt(yearKey) - b), 2));

            for (int i = nowYear + 1; i < b; i++) {
                double Y = a * Math.pow((i - b), 2) + c;
                //标准煤消耗量
                double comsum = Y / 2.69;

                double coalWeight = new BigDecimal(-0.1 * Math.pow(i, 2) + 401.34 * i - 402613)
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                if (coalWeight > 43) {
                    coalWeight = coalWeight - (new Random().nextInt(2) + 1);
                }
                double oilWeight = new BigDecimal(0.046741 * Math.pow(i, 2) - 187.9876 * i + 189030)
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                if (oilWeight > 20) {
                    List<Integer> l = Arrays.asList(-1, 1);
                    Integer v = l.get(new Random().nextInt(2));
                    oilWeight += v;
                }
                double airWeight = new BigDecimal(0.0141972 * Math.pow(i, 2) - 56.74455 * i + 56702)
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                if (airWeight > 12) {
                    List<Integer> l = Arrays.asList(-1, 1);
                    Integer v = l.get(new Random().nextInt(2));
                    airWeight += v;
                }
                //计算完权重后，按照标准煤和各个化石燃料的转换系数得到各个化石燃料的使用量并画图
                double coalComsume = new BigDecimal(comsum * coalWeight * 0.01 * 1000 / 0.7143 / 1000)
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                double oilComsume = new BigDecimal(comsum * oilWeight * 0.01 * 1000 / 1.4286 / 1000)
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                double airComsume = new BigDecimal(comsum * airWeight * 0.01 * 1000 / 1.215 / 1000)
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

                JSONObject json = new JSONObject();
                json.put("consume", new BigDecimal(comsum).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                //json.put("coalWeight", coalWeight);
                //json.put("oilWeight", oilWeight);
                //json.put("airWeight", airWeight);
                json.put("coalComsume", coalComsume);
                json.put("oilComsume", oilComsume);
                json.put("airComsume", airComsume);

                resJson.put(String.valueOf(i), json);
            }

            System.out.println(resJson.toJSONString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}
