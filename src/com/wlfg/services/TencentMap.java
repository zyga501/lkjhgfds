package com.wlfg.services;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;

public class TencentMap {
    public static    float   getDistance(String lx,String ly,String Clx,String Cly){
        try {
            String responsestr = webgetpost.geturl("http://apis.map.qq.com/ws/distance/v1/?from="+lx+","+ly+"&to="+Clx+","+Cly+"&key=ZO4BZ-BZXK4-JN2UU-DIZMB-OWDS5-UKBVD");
            JSONObject job = JSONObject.fromObject(responsestr);
            return Float.parseFloat(JSONObject.fromObject(JSONArray.fromObject(JSONObject.fromObject(job.get("result")).get("elements")).get(0)).get("distance").toString());
        } catch (IOException e) {
        }
        return -1;
    }
}
