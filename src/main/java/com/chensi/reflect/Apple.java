package com.chensi.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/***********************************
 * @author chensi
 * @date 2022/4/11 10:18
 ***********************************/
public class Apple {

    public int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static void main(String[] args) throws Exception {
        //正常的调用
        Apple apple = new Apple();
        apple.setPrice(5);
        System.out.println("Apple price:" + apple.getPrice());

        //使用反射调用
        Class clz = Class.forName("com.chensi.reflect.Apple");
        Method setPriceMethod = clz.getMethod("setPrice", int.class);
        Constructor appleConstructor = clz.getConstructor();
        Object appleObj = appleConstructor.newInstance();
        setPriceMethod.invoke(appleObj, 14);

        Method getPriceMethod = clz.getMethod("getPrice");
        System.out.println("Apple price:" + getPriceMethod.invoke(appleObj));

        /**
         * 通过反射获取类属性、方法、构造器
         */
        Class cla = Apple.class;
        //通过Class对象的getFields()方法获取Class类的属性，但是无法获取私有属性
        Field[] fields = clz.getFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }

        //如果使用Class对象的getDeclareFields()方法则可以获取包括私有属性在内的所有属性。
        Field[] declaredFields = clz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
    }

}
