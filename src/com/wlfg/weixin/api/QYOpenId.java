package com.wlfg.weixin.api;

import net.sf.json.JSONObject;

public class QYOpenId extends WeixinAPI {
    private final static String OPENID_API = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token=%s&userid=%s&";

    public QYOpenId(String accessToken, String userId ) {
        accesstoken_ = accessToken;
        userid_=userId;
    }

    public String getOpenId() { return openid_; }

    @Override
    protected String getAPIUri() {
        return String.format(OPENID_API, accesstoken_,userid_);
    }

    @Override
    protected boolean parseResponse(String responseString) throws Exception {
        JSONObject jsonParse = JSONObject.fromObject(responseString);
        if (jsonParse.get("openid") != null) {
            openid_ = jsonParse.get("openid").toString();
            return true;
        }
        return false;
    }

    private String accesstoken_;
    private String userid_;
    private String openid_;
}
