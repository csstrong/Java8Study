package com.chensi.reflect.six;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

/***********************************
 * @author chensi
 * @date 2022/4/13 9:18
 ***********************************/
//实现对象之间参数的拷贝
public class CopyUtil {

    public static void getClassByBeanUtil(InnerClass dest, OuterClass source) {
        try {
            BeanUtils.copyProperties(dest, source);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("BeanUtils result:" + dest);
    }

    public static void getClassByPropertyUtil(InnerClass dest, OuterClass source) {
        try {
            PropertyUtils.copyProperties(dest, source);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println("PropertyUtils result:" + dest);
    }
}
