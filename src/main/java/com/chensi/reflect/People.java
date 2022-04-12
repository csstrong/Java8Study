package com.chensi.reflect;

/***********************************
 * @author chensi
 * @date 2022/4/11 10:46
 ***********************************/

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射允许运行中的java程序对自身进行检查，并能直接操作程序和内部属性和方法。
 * 获取Class对象；
 * 获取类中所有字段；
 * 获取类中的所有构造方法；
 * 获取类中的所有非构造方法。
 */
public class People {

    private String name;
    public int age;

    public People() {
    }

    private People(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String show(String msg) {
        System.out.println(name + "," + age + ",hello " + msg);
        return "testString";
    }

    public static void main(String[] args) throws Exception {

        // 获取 Class 对象
        Class peopleClass = Class.forName("com.chensi.reflect.People");
        // 获取所有构造方法
        Constructor[] declaredConstructorList = peopleClass.getDeclaredConstructors();
        for (Constructor declaredConstructor : declaredConstructorList) {
            System.out.println("declared Constructor: " + declaredConstructor);
        }
        // 获取公有构造方法
        Constructor[] constructorList = peopleClass.getConstructors();
        for (Constructor constructor : constructorList) {
            System.out.println("constructor: " + constructor);
        }

        System.out.println("==================");
        // 1.获取所有声明的函数
        Method[] declaredMethodList = peopleClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethodList) {
            System.out.println("declared Method: " + declaredMethod);
        }
        // 2.获取所有公有的函数
        Method[] methodList = peopleClass.getMethods();
        for (Method method : methodList) {
            System.out.println("method: " + method);
        }

        System.out.println("=======================");

        /**
         * 获取Class对象
         * 获取私有构造方法
         * 设置访问权限
         * 调用私有构造方法构建对象
         * 获取成员属性
         * 设置年龄为24
         * 获取私有非构造方法
         * 设置访问权限
         * 执行show方法，返回数据放入message
         */
        Class clz = Class.forName("com.chensi.reflect.People");
        Constructor declaredConstructor = clz.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        People people = (People) declaredConstructor.newInstance("chensi");

        Field age = clz.getField("age");
        age.set(people, 24);
        Method show = clz.getDeclaredMethod("show", String.class);
        show.setAccessible(true);
        show.invoke(people, "java");
    }
}

