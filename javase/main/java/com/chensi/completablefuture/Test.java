package com.chensi.completablefuture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * @author  chensi
 * @date  2022/7/26
 */
public class Test {

    @org.junit.Test
    public void Test1() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getProceAsync("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + "msecs");

        //执行更多任务，比如查询其他商店
        //dosomething();

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.printf("Price returned after " + retrievalTime + " msecs%n");
    }

    @org.junit.Test
    public void test2() {
        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop("BestPrice"));
        shops.add(new Shop("LetsSaveBig"));
        shops.add(new Shop("MyFavoriteShop"));
        shops.add(new Shop("BuyItAll"));
        shops.add(new Shop("habi"));
        shops.add(new Shop("cs"));
        shops.add(new Shop("chensi"));

        //List<String> resL = calRunTime(Test::findPrices, shops, "myPhone13");
        //findPricesFunction(Test::findPrices, shops, "myPhone13", "Stream流");
        for (int i = 0; i < 20; i++) {
            Shop shop = new Shop(UUID.randomUUID().toString());
            shops.add(shop);
        }
        System.out.println("shops size is: " + shops.size());

        findPricesFunction(Test::findPrices2, shops, "myPhone14", "并行流");
        findPricesFunction(Test::findPrices3, shops, "myPhone15", "CompletableFuture异步请求");

        System.out.println("Thread count: " + Runtime.getRuntime().availableProcessors());

        //findPricesFunction(Test::findPrices2, shops, "myPhone16", "并行流");
        //findPricesFunction(Test::findPrices3, shops, "myPhone17", "CompletableFuture异步请求");

    }

    @org.junit.Test
    public void test3() {
        Shop shop = new Shop("habi");
        String str = shop.getPrice2("iphone");
        System.out.println(str);
    }

    //申请价格折扣
    @org.junit.Test
    public void test4() {
        List<Shop> shops = getShopList();

        //List<Shop> pricesFunction = findPricesFunction(Test::findPrices4, shops,
        //    "Phone13", "申请价格折扣");
        List<Shop> pricesFunction = findPricesFunction(Test::findPrices5, shops,
            "Phone13", "CompletableFuture调用远程");
        System.out.println(pricesFunction);
    }

    @org.junit.Test
    public void test5() {
        List<Shop> shops = getShopList();
        long start = System.nanoTime();
        Stream<CompletableFuture<String>> priceStream = findPricesStream(shops, "myPhone");
        CompletableFuture[] futures = priceStream
            .map(f -> f.thenAccept(
            s -> System.out.println(s + " done in " +
                ((System.nanoTime() - start) / 1_000_000) + " msecs")
        ))
            .toArray(size -> new CompletableFuture[size]);
        /*allOf工厂方法接受一个CompletableFuture构成的数组，数组中的所有CompletableFuture对象
         *执行完成之后，返回一个CompletableFuture<Void>对象
         */
        CompletableFuture.allOf(futures).join();
        System.out.println("All shops have responded in " +
            ((System.nanoTime() - start) / 1_000_000) + " msecs");
    }

    public List<Shop> getShopList() {
        List<Shop> shops = new ArrayList<>();
        shops.add(new Shop("BestPrice"));
        shops.add(new Shop("LetsSaveBig"));
        shops.add(new Shop("MyFavoriteShop"));
        shops.add(new Shop("BuyItAll"));
        return shops;
    }

    public static List<Shop> findPricesFunction(FindPricesFunction<List, List, String> of,
                                                List<Shop> shops, String product, String callType) {
        long start = System.nanoTime();
        List prices = of.findPrices(shops, product);
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf(callType + " :  consume " + duration + " msecs%n");
        return prices;
    }


    private List<String> calRunTime(BiFunction<List<Shop>, String, List> f,
                                    List<Shop> shops, String product) {
        long start = System.nanoTime();
        List<String> apply = f.apply(shops, product);
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.printf("BiFunction Done in " + duration + " msecs%n");
        return apply;
    }


    public static Executor getExecutor(List<Shop> shops) {
        Executor executor =
            Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        //使用守护线程，这种方式不会阻止程序的关停
                        t.setDaemon(true);
                        return t;
                    }
                });
        return executor;
    }

    /**
     * 使用Stream流遍历商品计算价格
     *
     * @param product 产品名
     * @return 字符串列表，包括商店的名称，该商店中指定商品的价格
     */
    public static List<String> findPrices(List<Shop> shops, String product) {
        return shops.stream()
            .map(shop -> String.format("%s price is %.2f",
                shop.getName(), shop.getPrice(product)))
            .collect(Collectors.toList());
    }

    /**
     * 使用并行流对请求进行并行操作
     *
     * @param shops
     * @param product
     * @return
     */
    public static List<String> findPrices2(List<Shop> shops, String product) {
        return shops.parallelStream()
            .map(shop -> String.format("%s price is %.2f",
                shop.getName(), shop.getPrice(product)))
            .collect(Collectors.toList());
    }

    /**
     * 使用CompletableFuture发起异步请求
     *
     * @param shops
     * @param product
     * @return 220706660147
     */
    public static List<String> findPrices3(List<Shop> shops, String product) {
        Executor executor = getExecutor(shops);

        List<CompletableFuture<String>> priceFutures = shops.stream()
            //使用CompletableFuture以异步方式计算每种商品的价格
            .map(shop -> CompletableFuture.supplyAsync(
                () -> shop.getName() + " price is " + shop.getPrice(product), executor))
            .collect(Collectors.toList());
        //等待所有异步操作结束
        return priceFutures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }

    public static List<String> findPrices4(List<Shop> shops, String product) {
        return shops.stream()
            .map(shop -> shop.getPrice2(product))
            .map(Quote::parse)
            .map(Discount::applyDiscount)
            .collect(Collectors.toList());
    }

    //构造同步和异步操作
    public static List<String> findPrices5(List<Shop> shops, String product) {
        Executor executor = getExecutor(shops);
        List<CompletableFuture<String>> priceFutures = shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(
                () -> shop.getPrice2(product), executor
            ))
            //Quote对象存在时，对其返回值进行转换
            .map(future -> future.thenApply(Quote::parse))
            //使用另一个异步任务构造期望的Future，申请折扣
            //thenCompose方法允许你对两个异步操作进行流水线
            .map(future -> future.thenCompose(quote ->
                CompletableFuture.supplyAsync(
                    () -> Discount.applyDiscount(quote), executor
                )))
            .collect(Collectors.toList());

        return priceFutures.stream()
            .map(CompletableFuture::join)
            .collect(Collectors.toList());
    }

    //重构findPrices方法返回一个由Future构成的流
    public Stream<CompletableFuture<String>> findPricesStream(List<Shop> shops, String product) {
        Executor executor = getExecutor(shops);
        return shops.stream()
            .map(shop -> CompletableFuture.supplyAsync(
                () -> shop.getPrice2(product), executor))
            .map(future -> future.thenApply(Quote::parse))
            .map(future -> future.thenCompose(quote ->
                CompletableFuture.supplyAsync(
                    () -> Discount.applyDiscount(quote), executor
                )));
    }
}
