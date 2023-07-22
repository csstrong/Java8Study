package com.chensi.reflect.five;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/***********************************
 * @author chensi
 * @date 2022/4/12 19:36
 ***********************************/
public class Intermediary implements InvocationHandler {

    private Object post;

    public Intermediary(Object post) {
        this.post = post;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object invoke = method.invoke(post, args);
        System.out.println("代理类：打印日志");
        return invoke;
    }
}
