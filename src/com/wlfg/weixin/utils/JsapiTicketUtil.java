package com.wlfg.weixin.utils;

import com.wlfg.weixin.api.AccessToken;
import net.sf.json.JSONObject;
import utils.HttpClient;
import utils.ProjectSettings;

import java.util.HashMap;
import java.util.Map;


public class JsapiTicketUtil  extends HttpClient {

    private final static String JSAPITICK_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
    private static Map<String, JsapiTicketUtil> jsApiTicketMap_ = new HashMap<>();
    @Override
    protected String getAPIUri() {
        try {
            return String.format(JSAPITICK_URL, AccessToken.getAccessToken(ProjectSettings.getMapData("weixinServerInfo").get("appid").toString()));
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

    private void updateJsApiTicketA() throws Exception {
        synchronized (this) {
            this.getRequest();
        }
    }

    public static void updateJsApiTicket(String  appid) throws Exception {
        synchronized(jsApiTicketMap_) {
                if (jsApiTicketMap_.get(appid)!=null){
                    jsApiTicketMap_.get(appid).getRequest();
                }
            else {
                    jsApiTicketMap_.put(appid,new JsapiTicketUtil() );
                    jsApiTicketMap_.get(appid).getRequest();
                }
            }
    }

    public static String getJsApiTicket(String appid){
       return  jsApiTicketMap_.get(appid).getJsApiTicket();
    }

    private String getJsApiTicket() { return jsapiticket_; }
/*
    public static String sendGet(String url, String charset, int timeout)
    {
        String result = "";
        try
        {
            URL u = new URL(url);
            try
            {
                URLConnection conn = u.openConnection();
                conn.connect();
                conn.setConnectTimeout(timeout);
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
                String line="";
                while ((line = in.readLine()) != null)
                {

                    result = result + line;
                }
                in.close();
            } catch (IOException e) {
                return result;
            }
        }
        catch (MalformedURLException e)
        {
            return result;
        }

        return result;
    }

    public static String getJSApiTicket() throws Exception {
        //获取token  ygai h缓存
        String acess_token=  AccessToken.getAccessToken(ProjectSettings.getMapData("weixinServerInfo").get("appid").toString());
        String urlStr = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+acess_token+"&type=jsapi";
        String backData=sendGet(urlStr, "utf-8", 10000);
        String ticket = (String) JSONObject.fromObject(backData).get("ticket");
        return  ticket;

    }
*/
    private  String jsapiticket_;
}