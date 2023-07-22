package com.chensi.reflect.six;

import java.util.Date;

/***********************************
 * @author chensi
 * @date 2022/4/13 9:24
 ***********************************/
public class TestIntermediary {
    public static void main(String[] args) {
        // 基本的代理类
        OuterClass outerClass = new OuterClass(1L, "shgx", 1, 23.5, new Date());
        // BeanUtil 完成对象拷贝
        InnerClass innerClassOne = new InnerClass();
        CopyUtil.getClassByBeanUtil(innerClassOne, outerClass);
        GetObject.runObject(innerClassOne);

        // PropertyUtil
        InnerClass innerClassTwo = new InnerClass();
        CopyUtil.getClassByPropertyUtil(innerClassTwo, outerClass);
        GetObject.runObject(innerClassTwo);

        // 置空
        outerClass.setId(null);
        outerClass.setName(null);
        outerClass.setAge(null);
        outerClass.setSex(null);
        outerClass.setBirthDay(new Date());
        // BeanUtil
        InnerClass innerClassThree = new InnerClass();
        CopyUtil.getClassByBeanUtil(innerClassThree, outerClass);
        GetObject.runObject(innerClassThree);

        // PropertyUtil
        CopyUtil.getClassByPropertyUtil(innerClassTwo, outerClass);
        GetObject.runObject(innerClassTwo);
    }
}
