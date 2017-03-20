package com.wlfg.weixin.api;

import com.wlfg.utils.StringsTools;
import net.sf.json.JSONObject;

import java.util.Map;

public class QYUserIdInfo extends WeixinAPI {
    private final static String OPENID_API = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=%s&userid=%s";

    public QYUserIdInfo(String accesstoken , String userid) {
        accesstoken_ =accesstoken;
        userid_ = userid;
    }

    public Map getMsgMap() { return msgmap_; }

    @Override
    protected String getAPIUri() {
        return String.format(OPENID_API, accesstoken_, userid_);
    }

    @Override
    protected boolean parseResponse(String responseString) throws Exception {
        JSONObject jsonParse = JSONObject.fromObject(responseString);
        if (jsonParse.get("userid") != null) {
            msgmap_ = StringsTools.json2Map(jsonParse);
            return true;
        }
        return false;
    }

    private String accesstoken_;
    private String userid_;
    private Map msgmap_;
}
