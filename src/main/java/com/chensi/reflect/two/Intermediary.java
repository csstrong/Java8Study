package com.chensi.reflect.two;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/***********************************
 * @author chensi
 * @date 2022/4/11 13:24
 ***********************************/
//基本的代理类，继承InvocationHandler接口，实现invoke方法
public class Intermediary implements InvocationHandler {
    private Object post;
    public Intermediary(Object post){
        this.post=post;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
        Object invoke = method.invoke(post, args);
        System.out.println("代理类：打印日志");
        return invoke;
    }
}
