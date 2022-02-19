package com.wangwenjun.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

/**
 * Created by wangwenjun on 2016/10/22.
 */
public class StreamInAction {
    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );
        //1）Function<T, R> => R apply(T t) ———— 接受一个T类型的参数，返回R类型结果。
        Function<Integer, String> function1 = (x) -> "result: " + x;
        function1.apply(6);

        //2）Consumer<T> => void accept(T t) ———— 接受一个T类型的参数，无返回。
        Consumer<String> consumer = (x) -> System.out.println("consumer: " + x);
        consumer.accept("Hello");

        //3)Predicate<T> => boolean test(T t) ———— 接受一个T类型的参数，返回布尔值。
        Predicate<String> predicate = (x) -> x.length() > 0;
        predicate.test("String");

        //（4）Supplier<T> => T get() ———— 无输入参数，返回T类型的一个结果。
        Supplier<String> supplier = () -> "Test supplier";
        supplier.get();


        //1. Find all transactions in the year 2011 and sort them by value (small to high).
        List<Transaction> result = transactions.stream().filter(transaction -> transaction.getYear() == 2011)
            .sorted(Comparator.comparing(Transaction::getValue))
            .collect(toList());


        System.out.println(result);


        //2.What are all the unique cities where the traders work?
        transactions.stream().map(t -> t.getTrader().getCity())
            .distinct().forEach(System.out::println);
        System.out.println("==================");

        //3.Find all traders from Cambridge and sort them by name.
        transactions.stream().map(Transaction::getTrader)
            .filter(t -> t.getCity().equals("Cambridge"))
            .distinct()
            .sorted(Comparator.comparing(Trader::getName))
            .forEach(System.out::println);

        //4.Return a string of all traders’ names sorted alphabetically

        String value = transactions.stream().map(t -> t.getTrader().getName())
            .distinct()
            .sorted()
            .reduce("", (name1, name2) -> name1 + name2);
        System.out.println(value);


        //5. Are any traders based in Milan?

        boolean liveInMilan1 = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        boolean liveInMilan2 = transactions.stream().map(Transaction::getTrader).anyMatch(t -> t.getCity().equals("Milan"));

        System.out.println(liveInMilan1);
        System.out.println(liveInMilan2);

        //6.Print all transactions’ values from the traders living in Cambridge.

        transactions.stream().filter(t -> t.getTrader().getCity().equals("Cambridge"))
            .map(Transaction::getValue)
            .forEach(System.out::println);


        //7.What’s the highest value of all the transactions?
        Optional<Integer> maxValue = transactions.stream().map(Transaction::getValue).reduce((i, j) -> i > j ? i : j);
        System.out.println(maxValue.get());

        //8.Find the transaction with the smallest value.
        Optional<Integer> minValue = transactions.stream().map(Transaction::getValue).reduce(Integer::min);
        System.out.println(minValue.get());
    }

}
