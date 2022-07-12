package com.chensi.util.test;

import okhttp3.*;
import org.junit.Test;

import java.util.regex.Pattern;

/***********************************
 * @author chensi
 * @date 2022/6/28 11:11
 ***********************************/

public class httpTest {
    @Test
    private void getGisData() {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "{'datasetNames':[\"pipe3D:pipeNetWork\"],'getFeatureMode':\"SQL\",'queryParameter':{'name':\"pipeNetWork\",'attributeFilter':\"\"},'maxFeatures':99999999,'hasGeometry':true}");
            Request request = new Request.Builder()
                .url("http://36.7.84.128:9691/iserver/services/data-pipe3D/rest/data/featureResults.json?returnContent=true&fromIndex=0&toIndex=1")
                .method("POST", body)
                .addHeader("Content-Type", "text/plain")
                .build();
            Response response = client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
