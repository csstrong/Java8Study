package com.chensi.reflect.six;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/***********************************
 * @author chensi
 * @date 2022/4/13 9:13
 ***********************************/
public class GetObject {

    //反射+动态代理的方式调用Parents里面的方法

    public static void runObject(final Parents post) {
        //调用方法时的处理器，本质都是在调用invoke()方法
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object invok = method.invoke(post, args);
                return invok;
            }
        };
        //参数:产生这个代理类的classLoader,实现了这个代理类的接口,h
        Object o = Proxy.newProxyInstance(Parents.class.getClassLoader(), new Class[]{Parents.class}, h);
        Parents parents = (Parents) o;
        parents.function();
    }
}
