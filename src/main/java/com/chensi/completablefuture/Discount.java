package com.chensi.completablefuture;

import java.util.List;
import java.util.stream.Collectors;

/*
 * @author  chensi
 * @date  2022/7/26
 */
public class Discount {
    public enum Code {
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;

        Code(int percentage) {
            this.percentage = percentage;
        }
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String applyDiscount(Quote quote) {
        return quote.getShopName() + " price is " +
            Discount.apply(quote.getPrice(), quote.getDiscountCode());
    }

    private static double apply(double price, Code code) {
        delay();
        return price * (100 - code.percentage) / 100;
    }

}
