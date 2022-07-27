package com.chensi.completablefuture;

/*
 * @author  chensi
 * @date  2022/7/26
 */
@FunctionalInterface
public interface FindPricesFunction<R,T1,T2> {
    //List<String> findPrices(List<Shop> shops, String product);
    R findPrices(T1 t1,T2 t2);
}
