package com.chensi.reflect.four;

/***********************************
 * @author chensi
 * @date 2022/4/12 19:16
 ***********************************/
public class ServiceOne extends BaseService {
    @Override
    String run(String something) {
        return "ServiceOne " + something;
    }
}
