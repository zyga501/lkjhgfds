package com.wlfg.weixin.api;

import net.sf.json.JSONObject;

public class QYUserId extends WeixinAPI {
    private final static String OPENID_API = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=%s&code=%s";

    public QYUserId(String accesstoken , String code) {
        accesstoken_ =accesstoken;
        code_ = code;
    }

    public String getUserId() { return userid_; }

    @Override
    protected String getAPIUri() {
        return String.format(OPENID_API, accesstoken_, code_);
    }

    @Override
    protected boolean parseResponse(String responseString) throws Exception {
        JSONObject jsonParse = JSONObject.fromObject(responseString);
        if (jsonParse.get("UserId") != null) {
            userid_ = jsonParse.get("UserId").toString();
            return true;
        }
        return false;
    }

    private String accesstoken_;
    private String code_;
    private String userid_;
}
