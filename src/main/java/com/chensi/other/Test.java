package com.chensi.other;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    @org.junit.Test
    public void test9() {
        String regx = "^[0-9]+°[0-9]+'([0-9]+|[0-9]+\\.[0-9]+)\\\"$";
        Pattern pattern = Pattern.compile(regx);
        if (pattern.matcher("111°11'11\"").matches()) {
            System.out.println("success");
        } else {
            System.out.println("false");
        }
    }

    @org.junit.Test
    public void test10() {
        String s = "FFFF";
        String[] split = s.split("");
        System.out.println();

        String binary = "87";
        int decimals = 2;
        int i = Integer.parseInt(binary, 16);
        String vStr = String.valueOf(i / Math.pow(10, decimals));

        System.out.println(vStr);
    }

    @org.junit.Test
    public void test11() {
        for (int i = 0; i < 10; i++) {
            int v = new Random().nextInt(2) + 1;
            System.out.println(v);
        }
    }

    @org.junit.Test
    public void test12() {
        JSONObject json1 = new JSONObject();
        JSONObject json2 = new JSONObject();
        json1.put("one", 1);
        json1.put("two", 2);
        json1.put("three", 3);

        json2.put("two", 4);
        json2.put("three", 5);
        json2.put("four", 6);

        json1.putAll(json2);

        System.out.println(json1.toJSONString());
        System.out.println(json2.toJSONString());
    }

    @org.junit.Test
    public void isMatcherValue() {
        String key = "chensi";
        String text = "chensi111";
        String regx = "(" + key + ")";
        Pattern pattern = Pattern.compile(regx);
        Matcher mather = pattern.matcher(text);
        if (!mather.find()) {
            System.out.println("false");
        } else {
            System.out.println("true");
        }
    }

    @Data
    class User {
        private Long id;
        private String userName;
    }

    @Data
    class Account {
        private Long id;
        private String userName;
    }

    List<Account> accounts = new ArrayList();
    List<User> users = new ArrayList();

    public Map<Long, String> getIdNameMap(List<User> users) {
        return users.stream().collect(Collectors.toMap(User::getId, User::getUserName));
    }

    //第二种：将id和实体Bean做为K,V account -> account是一个返回本身的lambda表达式，后面的使用Function接口中的一个默认方法代替，使整个方法更简洁优雅。
    public Map<Long, Account> getIdAccountMap(List<Account> accounts) {
        return accounts.stream().collect(Collectors.toMap(Account::getId, account -> account));
    }

    public Map<Long, Account> getIdAccountMap2(List<Account> accounts) {
        return accounts.stream().collect(Collectors.toMap(Account::getId, Function.identity()));
    }

    //第三种： key存在重复记录时处理,如果使用第一种方法会出错，所以这里只是简单的使用后者覆盖前者来解决key重复问题
    public Map<String, Account> getNameAccountMap(List<Account> accounts) {
        return accounts.stream().collect(Collectors.toMap(Account::getUserName, Function.identity(), (key1, key2) -> key2));
    }

    //第四种： 使用某个具体的Map类来保存，如保存时使用LinkedHashMap
    public Map<String, Account> getNameAccountMap2(List<Account> accounts) {
        return accounts.stream().collect(Collectors.toMap(Account::getUserName, Function.identity(), (key1, key2) -> key2, LinkedHashMap::new));
    }

}
