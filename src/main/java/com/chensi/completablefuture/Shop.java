package com.chensi.completablefuture;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/*
 * @author  chensi
 * @date  2022/7/26
 */
public class Shop {

    private String product;

    public Shop(String product) {
        this.product = product;
    }

    public String getName() {
        return product;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    //将同步方法转换为异步方法
    public Future<Double> getProceAsync(String product) {
        //创建CompletableFuture对象，它会包含计算的结果
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        //在另一个线程中以异步线程的方式执行计算
        new Thread(() -> {
            try {
                double price = calculatePrice(product);
                //需长时间计算的任务并得出结果时，设置Future返回值
                futurePrice.complete(price);
            } catch (Exception e) {
                //可以体现出线程中出现的错误
                futurePrice.completeExceptionally(e);
            }
        }).start();
        return futurePrice;
    }

    //使用工厂方法supplyAsync创建CompletetableFuture
    public Future<Double> getProceAsync2(String product) {
        /*
         supplyAsync方法接受一个生产者（Supplier）,返回一个CompletableFuture对象，
         该对象完成异步执行后会读取调用生产者方法的返回值。
         生产者方法会交给ForkJoinPool池中的某个执行线程（Executor）运行。
        */
        return CompletableFuture.supplyAsync(() -> calculatePrice(product));
    }

    public String getPrice2(String product) {
        Random random = new Random();
        double price = calculatePrice2(product);
        Discount.Code code = Discount.Code.values()[
            random.nextInt(Discount.Code.values().length)];
        return String.format("%s:%.2f:%s", product, price, code);
    }

    private double calculatePrice2(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    private static final Random random = new Random();

    public static void randomDelay() {
        int delay = 500 + random.nextInt(2000);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
