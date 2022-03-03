package com.chensi.other;

import java.math.BigDecimal;
import java.util.UUID;

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
    public void generateUUID(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString().replace("-",""));
    }
}
