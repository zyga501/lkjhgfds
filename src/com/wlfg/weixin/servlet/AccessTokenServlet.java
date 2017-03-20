package com.wlfg.weixin.servlet;


import com.wlfg.weixin.api.AccessToken;
import com.wlfg.weixin.api.QYAccessToken;
import com.wlfg.weixin.utils.JsapiTicketUtil;
import utils.ProjectSettings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

public class AccessTokenServlet extends HttpServlet {
    private class AccessTokenThread implements Runnable {
        public void run() {
            while (true) {
                try {
                    List<AccessToken> appidList = new ArrayList<>();
                    appidList.add(new AccessToken(ProjectSettings.getMapData("weixinServerInfo").get("appid").toString(), ProjectSettings.getMapData("weixinServerInfo").get("appSecret").toString()));
                    AccessToken.updateAccessToken(appidList);
                    JsapiTicketUtil.updateJsApiTicket(ProjectSettings.getMapData("weixinServerInfo").get("appid").toString());

                    List<QYAccessToken> qyappidList = new ArrayList<>();
                    qyappidList.add(new QYAccessToken(ProjectSettings.getMapData("weixinServerInfo").get("qyappid").toString(), ProjectSettings.getMapData("weixinServerInfo").get("qyappSecret").toString()));
                    QYAccessToken.updateQYAccessToken(qyappidList);
                    // 休眠7000秒
                    Thread.sleep((DEFAULTEXPIRESTIME - 200) * 1000);
                }
                catch (Exception exception) {
                    try {
                        // 60秒后再获取
                        Thread.sleep(60 * 1000);
                    }
                    catch (InterruptedException e1) {
                    }
                }
            }
        }

        private final static int DEFAULTEXPIRESTIME = 7200;
    }

    public void init() throws ServletException {
        new Thread(new AccessTokenThread()).start();
    }
}
