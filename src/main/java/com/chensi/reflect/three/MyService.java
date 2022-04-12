package com.chensi.reflect.three;

/***********************************
 * @author chensi
 * @date 2022/4/11 13:43
 ***********************************/
//自定义一个服务
public class MyService {
    public String run(String something) {
        // int i=1/0;
        return "服务正在运行..." + something;
    }
}
