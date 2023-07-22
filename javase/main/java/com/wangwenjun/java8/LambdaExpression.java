package com.wangwenjun.java8;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by wangwenjun on 2016/10/12.
 */
public class LambdaExpression {

    public static void main(String[] args) {

        Comparator<Apple> byColor = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };

        List<Apple> list = Collections.emptyList();

        list.sort(byColor);

        Comparator<Apple> byColor2 = (o1, o2) -> o1.getColor().compareTo(o2.getColor());

        Function<String, Integer> flambda = s -> s.length();
        Integer sLen = flambda.apply("hello");
        System.out.println("length="+sLen);

        Predicate<Apple> p = (Apple a) -> a.getColor().equals("green");
        boolean green = p.test(new Apple("green", 100));
        System.out.println("Apple color equal green?"+green);

        Runnable r = ()->{};


        Function<Apple,Boolean> f = (a)->a.getColor().equals("green");
        Boolean isGreen = f.apply(new Apple("green", 40));
        System.out.println("isGreen?"+isGreen);

        Supplier<Apple> supplier=Apple::new;
        Apple apple = supplier.get();
        System.out.println(apple.toString());
    }
}
