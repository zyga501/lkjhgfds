package com.wlfg.weixin.api;

import net.sf.json.JSONObject;
import utils.HttpClient;
import utils.ProjectSettings;

import java.util.HashMap;
import java.util.Map;

public class JsapiTicket extends HttpClient {
    private final static String JSAPITICK_URL = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=%s";
    private static Map<String, JsapiTicket> jsApiTicketMap_ = new HashMap<>();

    public static String getJsApiTicket(String appid) throws Exception {
        synchronized(jsApiTicketMap_) {
            if (jsApiTicketMap_.get(appid) != null) {
                return jsApiTicketMap_.get(appid).getJsApiTicket();
            }

            updateJsApiTicket(appid);
            return getJsApiTicket(appid);
        }
    }

    public static void updateJsApiTicket(String appid) throws Exception {
        synchronized(jsApiTicketMap_) {
            if (jsApiTicketMap_.get(appid)!=null){
                jsApiTicketMap_.get(appid).getRequest();
            }
            else {
                jsApiTicketMap_.put(appid,new JsapiTicket() );
                jsApiTicketMap_.get(appid).getRequest();
            }
        }
    }

    @Override
    protected String getAPIUri() {
        try {
            return String.format(JSAPITICK_URL, QYAccessToken.getQYAccessToken(ProjectSettings.getMapData("weixinServerInfo").get("qyappid").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    protected boolean parseResponse(String responseString) throws Exception {
        JSONObject jsonParse = JSONObject.fromObject(responseString);
        if (jsonParse.get("ticket") != null) {
            jsapiticket_ = jsonParse.get("ticket").toString();
            return true;
        }
        return false;
    }

    public String getJsApiTicket() { return jsapiticket_; }

    private  String jsapiticket_;
}
