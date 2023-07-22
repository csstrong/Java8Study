package com.chensi.reflect.one;

import java.util.HashMap;
import java.util.Map;

/***********************************
 * @author chensi
 * @date 2022/4/11 11:34
 ***********************************/
//工厂方法
public class Factory {

    private static Map<String, String> map = new HashMap<>();

    static {
        map.clear();
        //模拟注册中心，存放实现类
        map.put("one.Children", "one.Children");
        map.put("one.Student", "one.Student");
    }

    public static void main(String[] args) {
        run("one.Student");
    }

    private static void run(String clzName) {
        if (null == clzName || clzName.length() == 0 || "".equals(clzName)) {
            return;
        }
        if (!map.containsKey(clzName)) {
            System.out.println("paramter not allow empty!");
            return;
        }
        try {
            Class clz = Class.forName("com.chensi.reflect." + map.get(clzName));
            Parents o = (Parents) clz.newInstance();
            System.out.println("run function:" + clzName);
            o.function();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
