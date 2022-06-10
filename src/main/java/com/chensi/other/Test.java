package com.chensi.other;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public void test3() {
        Map<JSONObject, JSONObject> dataMap = new HashMap<>();
        JSONObject jsonKey = new JSONObject();
        jsonKey.put("company_id", "a");
        jsonKey.put("year", "2021");
        JSONObject jsonValue = new JSONObject();
        jsonValue.put("a", "1");
        jsonValue.put("b", "2");
        jsonValue.put("c", "3");
        dataMap.put(jsonKey, jsonValue);

        Collection<JSONObject> values = dataMap.values();
        JSONObject[] jsonObjects = values.toArray(new JSONObject[0]);
        List<JSONObject> jsonObjectList = Arrays.asList(jsonObjects);
        System.out.println();
    }

    @org.junit.Test
    public void test4() {
        String str = "[\"gw1\",\"gw2\"]";
        //String str = "[\"3.28-6.47\"]";
        str = str.replaceAll("\\[", "");
        str = str.replaceAll("\\]", "");
        str = str.replaceAll("\\\"", "");

        System.out.println(str);

        List<String> collect = Stream.of(str.split(",")).collect(toList());
        System.out.println(collect);
    }

    @org.junit.Test
    public void test5() {
        String value = "";
        String v = new BigDecimal(String.valueOf(value)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        System.out.println(v);
    }

    @org.junit.Test
    public void test6() {
        double random = Math.random();
        System.out.println(random * 100);
    }

    @org.junit.Test
    public void test7() {
        JSONObject json = new JSONObject();
        json.put("1", 10);
        json.put("2", 5);
        json.put("3", 20);

        //根据json的value进行排序
        JSONObject resJson = new JSONObject();
        List<Map.Entry<String, Object>> collect = json.entrySet().stream().sorted(Comparator.comparing(s -> -1 * (Integer) s.getValue()))
            .collect(toList());
        System.out.println(collect);

        JSONObject j = new JSONObject(new LinkedHashMap<>());
        collect.stream().map(m -> j.put(m.getKey(), m.getValue())).collect(toList());
        System.out.println(j);
    }

    @org.junit.Test
    public void test8() {
        aaa:
        for (int j = 0; j < 3; j++) { // j=0 外层for循环
            bbb:
            for (int i = 0; i < 2; i++) { // i=0 内层for循环
                System.out.println("hello world"); // 1
                if (j == 1) {
                    break aaa;
                }
            }
        }
    }
}
