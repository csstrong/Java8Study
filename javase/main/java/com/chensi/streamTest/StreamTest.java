package com.chensi.streamTest;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/***********************************
 * @author chensi
 * @date 2022/3/1 10:57
 ***********************************/
public class StreamTest {
    @Test
    public void testStreamJsonParse() {
        String str = "{\n" +
            "    \"result\": 1,\n" +
            "    \"status\": 200,\n" +
            "    \"data\": {\n" +
            "        \"areaAssertTotalByYear\": \"xx\",\n" +
            "        \"monitorEmissionSumByYear\": \"xx\",\n" +
            "        \"examineEmissionByMonth\": \"xx\",\n" +
            "        \"monitorEmissionByMonth\": \"xx\"\n" +
            "    },\n" +
            "    \"total\": 4\n" +
            "}";
        List<String> list = new ArrayList<>();
        list.add(str);
        String total = list.stream().map(JSONObject::parseObject)
            .map(JSONObject::toString).findFirst().get();
        System.out.println(total);
    }
}
