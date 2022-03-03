package com.chensi.internet.demo;

/***********************************
 * @author chensi
 * @date 2022/3/2 17:22
 ***********************************/

import org.junit.Ignore;
import org.junit.Test;

public class Test1 {

    //免费的在线REST服务, 提供测试用的HTTP请求假数据
    //接口信息说明可见：http://www.hangge.com/blog/cache/detail_2020.html
    String uri = "http://api.apishop.net/common/weather/get15DaysWeatherByArea";
    //String uri = "http://jsonplaceholder.typicode.com";

    //get方式请求数据
    //请求地址：http://jsonplaceholder.typicode.com/posts
    @Ignore("暂时忽略")
    @Test
    public void test1() {
        System.out.print("\n" + "test1---------------------------" + "\n");
        HttpParamers paramers = HttpParamers.httpGetParamers();
        paramers.addParam("apiKey","WM27Q6t41efc8335c8357573d2d72c883cea70843024742");
        paramers.addParam("areaID","34");
        String response = "";
        try {
            HttpService httpService = new HttpService(uri);
            response = httpService.service("", paramers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(response);
    }

    //get方式请求数据
    //请求地址：http://jsonplaceholder.typicode.com/posts?userId=5
    @Ignore("暂时忽略")
    @Test
    public void test2() {
        System.out.print("\n" + "test2---------------------------" + "\n");
        String uri2="http://192.168.200.112:8090/iserver/services/geometry/restjsr/v1/geometry/distance.json";
        HttpParamers paramers = HttpParamers.httpGetParamers();

        paramers.addParam("point2Ds", "[{\"x\": 23.00,\"y\":34.00},\n" +
            "{\"x\": 53.55,\"y\":12.66},\n" +
            "{\"x\": 73.88,\"y\":12.6}]");
        paramers.addParam("unit","KILOMETER");
        paramers.addParam("prjCoordSys","{epsgcode:4326}");
        paramers.addParam("distanceMode","Geodesic");
        String response = "";
        try {
            HttpService httpService = new HttpService(uri2);
            response = httpService.service("", paramers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(response);
    }

    //post方式请求数据
    //请求地址：http://jsonplaceholder.typicode.com/posts
    @Test
    public void test3() {
        System.out.print("\n" + "test3---------------------------" + "\n");
        HttpParamers paramers = HttpParamers.httpPostParamers();
        paramers.addParam("time", String.valueOf(System.currentTimeMillis()));
        String response = "";
        try {
            HttpService httpService = new HttpService(uri);
            response = httpService.service("/posts", paramers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.print(response);
    }
}
