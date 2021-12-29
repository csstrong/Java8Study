package com.wangwenjun.java8;

/***************************************
 * @author:Alex Wang
 * @Date:2016/10/29 QQ:532500648
 * QQ交流群:286081824
 ***************************************/

import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static com.wangwenjun.java8.CollectorsAction.menu;

public class CollectorsAction3 {

    public static void main(String[] args) {
        //testPartitioningByWithPredicate();
        //testPartitioningByWithPredicateAndCollector();
        //testReducingBinaryOperator();
        //testReducingBinaryOperatorAndIdentiy();
        //testReducingBinaryOperatorAndIdentiyAndFunction();
        //testSummarizingDouble();
        //testSummarizingLong();
        //testSummarizingInt();
    }

    @Test
    public void testPartitioningByWithPredicate() {
        System.out.println("testPartitioningByWithPredicate");
        Map<Boolean, List<Dish>> collect = menu.stream().collect(Collectors.partitioningBy(Dish::isVegetarian));
        for (Map.Entry<Boolean, List<Dish>> entry : collect.entrySet()) {
            System.out.println(entry.getKey());
            List<Dish> dishList = entry.getValue();
            for (Dish dish : dishList) {
                System.out.println(dish.getName());
            }
        }
        Optional.of(collect).ifPresent(System.out::println);

    }

    @Test
    public void testPartitioningByWithPredicateAndCollector() {
        System.out.println("testPartitioningByWithPredicateAndCollector");
        Map<Boolean, Double> collect = menu.stream()
            .collect(Collectors.partitioningBy(Dish::isVegetarian, Collectors.averagingInt(Dish::getCalories)));
        Optional.of(collect).ifPresent(System.out::println);
    }

    @Test
    public void testReducingBinaryOperator() {
        System.out.println("testReducingBinaryOperator");
        menu.stream().collect(
            Collectors.reducing(
                BinaryOperator.maxBy(
                    Comparator.comparingInt(Dish::getCalories)
                )
            )
        ).ifPresent(System.out::println);
    }

    @Test
    public void testReducingBinaryOperatorAndIdentiy() {
        System.out.println("testReducingBinaryOperatorAndIdentiy");
        Integer result = menu.stream()
            .map(Dish::getCalories).collect(Collectors.reducing(0, (d1, d2) -> d1 + d2));
        System.out.println(result);
    }

    @Test
    public void testReducingBinaryOperatorAndIdentiyAndFunction() {
        System.out.println("testReducingBinaryOperatorAndIdentiyAndFunction");
        Integer result = menu.stream().collect(Collectors.reducing(0, Dish::getCalories, (d1, d2) -> d1 + d2));
        System.out.println(result);
    }

    @Test
    public void testSummarizingDouble() {
        System.out.println("testSummarizingDouble");
        Optional.of(menu.stream().collect(Collectors.summarizingDouble(Dish::getCalories)))
            .ifPresent(System.out::println);
    }

    @Test
    public void testSummarizingLong() {
        System.out.println("testSummarizingLong");
        Optional.of(menu.stream().collect(Collectors.summarizingLong(Dish::getCalories)))
            .ifPresent(System.out::println);
    }

    @Test
    public void testSummarizingInt() {
        System.out.println("testSummarizingLong");
        Optional.of(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories)))
            .ifPresent(System.out::println);
    }
}