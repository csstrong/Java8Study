package com.wangwenjun.java8;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by wangwenjun on 2016/10/18.
 */
public class SimpleStream {
    public static void main(String[] args) {
        //have a dish list (menu)

        List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

  /*      Stream<Dish> stream = menu.stream();
        stream.forEach(System.out::println);
        stream.forEach(System.out::println);*/

        Stream<Dish> dishStream = Stream.of(new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));
        dishStream.forEach(System.out::println);

        System.out.println("=========================");

        List<String> result = menu.stream().filter(d -> {

            System.out.println("filtering->" + d.getName());
            return d.getCalories() > 300;
        })
            .map(d -> {
                System.out.println("map->" + d.getName());
                return d.getName();
            })
            .limit(3).collect(toList());


        System.out.println("=======================");
        System.out.println(result);

        List<String> dishNamesByCollections = getDishNamesByCollections(menu);
        System.out.println(dishNamesByCollections);
      /*  List<String> dishNamesByStreams = getDishNamesByStream(menu);
        System.out.println(dishNamesByStreams);*/
    }

    private static List<String> getDishNamesByStream(List<Dish> menu) {
        return menu.parallelStream().filter(d -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return d.getCalories() < 400;
            }
        ).sorted(comparing(Dish::getCalories)).map(Dish::getName).collect(toList());
    }

    private static List<String> getDishNamesByCollections(List<Dish> menu) {
        List<Dish> lowCalories = new ArrayList<>();

        //filter and get calories less 400
        for (Dish d : menu) {
            if (d.getCalories() < 400)
                lowCalories.add(d);
        }

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //sort
        Collections.sort(lowCalories, (d1, d2) -> Integer.compare(d1.getCalories(), d2.getCalories()));

        List<String> dishNameList = new ArrayList<>();
        for (Dish d : lowCalories) {
            dishNameList.add(d.getName());
        }
        return dishNameList;
    }

    @Test
    public void testListStream() {
        List<String> list = new ArrayList<>();
        String s = "{\"component_time\":\"2022/01/19 14:00:00\",\"id\":\"2022010502\",\"IsVerify\":true,\"VerifyDate\":\"2022/01/19 14:47:10\",\"component_type\":\"21\",\"component_val\":{\"DataTime\":\"2022/01/19 14:00:00\",\"w00000-Avg\":\"1000\",\"w00000-Flag\":\"N\",\"w01001-Avg\":\"8.00\",\"w01001-Flag\":\"N\",\"w01018-Avg\":\"5.00\",\"w01018-Cou\":\"20\",\"w01018-Cou-Flag\":\"N\",\"w01018-Flag\":\"N\",\"w21001-Avg\":\"0.2\",\"w21001-Cou\":\"20\",\"w21001-Cou-Flag\":\"N\",\"w21001-Flag\":\"N\",\"w21003-Avg\":\"1\",\"w21003-Cou\":\"25\",\"w21003-Cou-Flag\":\"N\",\"w21003-Flag\":\"N\",\"w21011-Avg\":\"2\",\"w21011-Cou\":\"30\",\"w21011-Cou-Flag\":\"N\",\"w21011-Flag\":\"N\"},\"doc_store_time\":\"2022/01/19 14:47:10\",\"node_ip\":\"192.168.200.1:22202\"}";
        list.add(s);
        String component_val = list.stream().map(data -> JSONObject.parseObject(data))
            .map(m -> m.getJSONObject("component_val")).collect(toList()).toString();
        System.out.println(component_val);
    }

    @Test
    public void testJSONObject(){
        String s="{\"site_name\":\"濉溪路桥\",\"section_id\":\"NFH00504\",\"component_val\":{\"IsVerify\":true,\"w01010-Avg\":\"7.65\",\"w01009-Flag\":\"N\",\"w01003-Avg\":\"56.26\",\"DataTime\":\"2022/01/25 08:00:00\",\"w01001-Flag\":\"N\",\"w01014-Avg\":\"263.20\",\"w01003-Flag\":\"N\",\"w01019-Flag\":\"N\",\"VerifyDate\":\"2022/01/25 09:05:12\",\"w21003-Avg\":\"0.075\",\"w01014-Flag\":\"N\",\"w01001-Avg\":\"8.08\",\"w21011-Avg\":\"0.109\",\"w01019-Avg\":\"0.000\",\"w01009-Avg\":\"12.08\",\"w01010-Flag\":\"N\",\"w21003-Flag\":\"N\",\"w21011-Flag\":\"N\"},\"latitude\":\"31.87675\",\"site_id\":\"3\",\"monitor_type\":\"section\",\"longitude\":\"117.25362\"}";

        String s1="{\"component_val\":\"{\"IsVerify\":true,\"w01010-Avg\":\"7.65\"}\"";
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println(jsonObject);
    }
}
