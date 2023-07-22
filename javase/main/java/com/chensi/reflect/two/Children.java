package com.chensi.reflect.two;

import com.chensi.reflect.two.Parents;

/***********************************
 * @author chensi
 * @date 2022/4/11 13:23
 ***********************************/
public class Children implements Parents {

    @Override
    public void function() {
        System.out.println("I am children.");
    }
}
