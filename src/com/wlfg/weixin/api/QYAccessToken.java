package com.wlfg.weixin.api;

import net.sf.json.JSONObject;
import utils.HttpClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QYAccessToken extends HttpClient {
    private final static String QY_ACCESS_TOKEN_API = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=%s&corpsecret=%s";

    private static Map<String, QYAccessToken> QYAccessTokenMap_ = new HashMap<>();

    public static QYAccessToken getQYAccessTokenByQYAccessToken(String QYAccessToken) {
        synchronized(QYAccessTokenMap_) {
            if (QYAccessTokenMap_.containsValue(QYAccessToken)) {
                for (Map.Entry<String, QYAccessToken> entry : QYAccessTokenMap_.entrySet()) {
                    if (entry.getValue().getQYAccessToken().compareTo(QYAccessToken) == 0) {
                        return entry.getValue();
                    }
                }
            }

            return null;
        }
    }

    public static String getQYAccessToken(String appid) throws Exception {
        synchronized(QYAccessTokenMap_) {
            if (QYAccessTokenMap_.get(appid) != null) {
                return QYAccessTokenMap_.get(appid).getQYAccessToken();
            }

            throw new NoSuchFieldException("Unknown Appid!");
        }
    }

    public static void updateQYAccessToken(QYAccessToken QYAccessToken) throws Exception {
        synchronized(QYAccessTokenMap_) {
            if (QYAccessToken.getRequest()) {
                QYAccessTokenMap_.put(QYAccessToken.getAppid(), QYAccessToken);
            }
        }
    }

    public static String updateQYAccessToken(QYAccessToken QYAccessToken, String invalidQYAccessToken) throws Exception {
        synchronized(QYAccessTokenMap_) {
            if (!QYAccessTokenMap_.containsValue(invalidQYAccessToken)) {
                return getQYAccessToken(QYAccessToken.getAppid());
            }

            updateQYAccessToken(QYAccessToken);
            return getQYAccessToken(QYAccessToken.getAppid());
        }
    }

    public static void updateQYAccessToken(List<QYAccessToken> QYAccessTokenList) throws Exception {
        synchronized(QYAccessTokenMap_) {
            for (int index = 0; index < QYAccessTokenList.size(); ++index) {
                updateQYAccessToken(QYAccessTokenList.get(index));
            }
        }
    }

    public QYAccessToken(String appid, String appSecret) {
        appid_ = appid;
        appSecret_ = appSecret;
    }

    public String getQYAccessToken() { return QYAccessToken_; }


    public String getAppid() {
        return appid_;
    }

    public String getAppSecret() {
        return appSecret_;
    }

    @Override
    protected String getAPIUri() {
        return String.format(QY_ACCESS_TOKEN_API, appid_, appSecret_);
    }

    @Override
    protected boolean parseResponse(String responseString) throws Exception {
        JSONObject jsonParse = JSONObject.fromObject(responseString);
        if (jsonParse.get("access_token") != null) {
            QYAccessToken_ = jsonParse.get("access_token").toString();
            return true;
        }
        return false;
    }

    private String appid_;
    private String appSecret_;
    private String QYAccessToken_;
}