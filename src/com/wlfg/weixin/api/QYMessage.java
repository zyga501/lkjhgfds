package com.wlfg.weixin.api;

import net.sf.json.JSONObject;
import utils.HttpClient;

public class QYMessage extends HttpClient {
    private static final String SEND_MESSAGE = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=%s";

    public QYMessage(String accessToken) {
        accessToken_ = accessToken;
    }

    @Override
    public boolean postRequest(String postData) throws Exception {
        postData_ = postData;
        return super.postRequest(postData);
    }

    @Override
    protected String getAPIUri() {
        return String.format(SEND_MESSAGE, accessToken_);
    }

    @Override
    protected boolean parseResponse(String responseString) throws Exception {
        JSONObject jsonParse = JSONObject.fromObject(responseString);
        if (jsonParse.get("errcode") != null) {
            String errorCode = jsonParse.get("errcode").toString();
            switch (errorCode) {
                case "0": {
                    return true;
                }
                default:{
                    System.out.println("qymsg:"+errorCode);
                    return false;
                }
            }
        }
        return super.parseResponse(responseString);
    }
    public static void main(String[] args) throws Exception {

    }
    private String accessToken_;
    protected String postData_;
}
