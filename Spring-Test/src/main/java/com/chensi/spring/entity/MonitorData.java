package com.chensi.spring.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*
 * @author  chensi
 * @date  2022/8/17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonitorData implements Cloneable, Serializable {

    private String id;

    private String ip;

    private String monitorTime;

    private String stroageTime;

    private JSONObject componentVal;

}

