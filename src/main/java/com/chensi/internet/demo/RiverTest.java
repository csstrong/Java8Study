package com.chensi.internet.demo;

/***********************************
 * @author chensi
 * @date 2022/3/2 17:22
 ***********************************/

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.chensi.util.file.Demo.readConfigFile;

public class RiverTest {

    JSONObject resJson = new JSONObject();

    @Ignore("暂时忽略")
    @Test
    public void test() {
        HttpParamers paramers = HttpParamers.httpGetParamers();
        paramers.addParam("hasGeometry", "true");
        String response = "";
        HttpService httpService = null;
        try {
            for (int i = 0; i < 19; i++) {
                System.out.print("\n" + "river-id-" + i + "\n");
                String uri = "http://192.168.200.112:8090/iserver/services/data-Rivers/rest/data/feature/0-1-" + i + ".json";
                httpService = new HttpService(uri);
                response = httpService.service("", paramers);
                parseRiverJson(response);
            }
            System.out.println("success");
            //写riverMap到json文件
            if (resJson.size() > 0) {
                String source = resJson.toJSONString();
                File file = new File("src/main/assembly/conf/river.json");
                FileOutputStream fos = new FileOutputStream(file);
                fos.write(source.getBytes(StandardCharsets.UTF_8));
                fos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.print(response);
    }

    private void parseRiverJson(String str) {
        JSONObject riverJson = JSONObject.parseObject(str);
        List<String> fieldValues = (List<String>) riverJson.get("fieldValues");
        String name = fieldValues.get(11);
        JSONObject geometry = riverJson.getJSONObject("geometry");
        JSONArray points = geometry.getJSONArray("points");
        resJson.put(name, points);
    }

    //==========================


    @Test
    public void test2() {
        String name = "店埠河";
        List<Double> XY = Arrays.asList(117.4606914, 31.87354629999943);
        List<List<Object>> objects = riverInterfaceTest(name, XY);

        String uri2 = "http://192.168.200.112:8090/iserver/services/geometry/restjsr/v1/geometry/distance.json";
        HttpParamers paramers = HttpParamers.httpGetParamers();

        String pointArray1 = objects.get(0).toString();
        String pointArray2 = objects.get(1).toString();

        paramers.addParam("point2Ds", pointArray1);
        paramers.addParam("unit", "KILOMETER");
        paramers.addParam("prjCoordSys", "{epsgcode:4326}");
        paramers.addParam("distanceMode", "Geodesic");
        String response = "";
        try {
            HttpService httpService = new HttpService(uri2);
            response = httpService.service("", paramers);
            System.out.println("first distance: " + response);

            paramers.addParam("point2Ds", pointArray2);
            response = httpService.service("", paramers);
            System.out.println("second distance: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<List<Object>> riverInterfaceTest(String riverName, List<Double> XY) {

        List<List<Object>> list = new ArrayList<>();
        Double x = XY.get(0);
        Double y = XY.get(1);
        String RIVER_CONFIG = readConfigFile("river.json");
        JSONObject riverInfo = JSONObject.parseObject(RIVER_CONFIG);
        JSONArray XYArray = riverInfo.getJSONArray(riverName);
        System.out.println("XYArray.size(): " + XYArray.size());

        List<Object> collect1 = new ArrayList<>();
        List<Object> collect2 = new ArrayList<>();
        for (int i = 0; i < XYArray.size(); i++) {
            JSONObject json = XYArray.getJSONObject(i);
            Double x1 = json.getDouble("x");
            Double y1 = json.getDouble("y");
            if (x1.equals(x) && y1.equals(y)) {
                System.out.println("index: " + i);
                collect1 = XYArray.stream().limit(i + 1).collect(Collectors.toList());
                collect2 = XYArray.stream().skip(i).collect(Collectors.toList());
                break;
            }
        }
        System.out.println("collect1.size(): " + collect2.size());
        System.out.println("collect2.size(): " + collect2.size());
        list.add(collect1);
        list.add(collect2);
        return list;
    }
}
