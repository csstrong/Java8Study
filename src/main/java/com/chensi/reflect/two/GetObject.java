package com.chensi.reflect.two;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/***********************************
 * @author chensi
 * @date 2022/4/11 13:28
 ***********************************/
public class GetObject {
    /**
     * 优雅的代理类，直接返回对象。采用内部类的方式，将反射的方法当作参数传递给InvocationHandler
     * 反射+代理的方法调用Parents里面的方法
     */

    public static void runObject(final Parents post) {
        //调用方法的处理器，本质都是在调用invoke方法
        InvocationHandler h = new InvocationHandler() {
            /**
             * 调用方法的处理内容放在invoke里面
             * @param proxy 代理对象
             * @param method 调用的方法
             * @param args 传递的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object invoke = method.invoke(post, args);
                System.out.println("代理类：打印日志");
                return invoke;
            }
        };

        //参数：产生这个代理类的classLoader,实现了这个代理类的接口,h
        Object o = Proxy.newProxyInstance(Parents.class.getClassLoader(), new Class[]{Parents.class}, h);
        System.out.println(o.getClass().getName());
        System.out.println(o.getClass().getInterfaces()[0]);

        Parents parents = (Parents) o;
        parents.function();
        return;
    }
}
