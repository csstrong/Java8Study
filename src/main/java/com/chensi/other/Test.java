package com.chensi.other;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/***********************************
 * @author chensi
 * @date 2022/3/1 10:58
 ***********************************/
public class Test {
    @org.junit.Test
    public void testBigDecimal() {
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(0.00)).setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal.toString());
    }

    @org.junit.Test
    public void testStringCompare() {
        String s1 = "2021/01";
        String s2 = "2021";
        String s3 = "2022";
        System.out.println(s1.compareTo(s2) >= 0);
        System.out.println(s1.compareTo(s3) <= 0);
    }

    @org.junit.Test
    public void generateUUID() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString().replace("-", ""));
    }

    @org.junit.Test
    public void getFormula() {
        String x = "1";
        String y = "2";
        String z = "3";
        System.out.println("(" + x + ")" + "*x^2+(" + y + ")" + "*x+(" + z + ")");
    }

    @org.junit.Test
    public void testStream() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream()
            .flatMap(i ->
                numbers2.stream()
                    .filter(j -> (i + j) % 3 == 0)
                    .map(j -> new int[]{i, j})
            )
            .collect(toList());
    }

    @org.junit.Test
    public void test1() {
        String[] nameArray = new String[]{"businesstype", "type"};
        String s = JSON.toJSONString(nameArray);
        List<String> list = JSON.parseArray(s, String.class);
        System.out.println(list);
    }

    @org.junit.Test
    public void test2() {
        List<String> items = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");
//        Map<String,Long> map = items.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
//        Map<String, Long> map = items.stream().collect(Collectors.groupingBy(t -> t, Collectors.counting()));
        Map<Object, List<String>> map = items.stream().collect(Collectors.groupingBy(t -> t, toList()));
        System.out.println(JSON.toJSONString(map));

    }

    @org.junit.Test
    public void test3(){
        Map<JSONObject, JSONObject> dataMap = new HashMap<>();
        JSONObject jsonKey=new JSONObject();
        jsonKey.put("company_id","a");
        jsonKey.put("year","2021");
        JSONObject jsonValue=new JSONObject();
        jsonValue.put("a","1");
        jsonValue.put("b","2");
        jsonValue.put("c","3");
        dataMap.put(jsonKey,jsonValue);

        Collection<JSONObject> values = dataMap.values();
        JSONObject[] jsonObjects = values.toArray(new JSONObject[0]);
        List<JSONObject> jsonObjectList = Arrays.asList(jsonObjects);
        System.out.println();
    }
}
