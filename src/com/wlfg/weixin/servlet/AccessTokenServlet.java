package com.wlfg.weixin.servlet;


import com.wlfg.action.AjaxActionSupport;
import com.wlfg.weixin.api.QYAccessToken;
import utils.ProjectSettings;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccessTokenServlet extends HttpServlet {
    private class AccessTokenThread implements Runnable {
        public void run() {
            while (true) {
                try {
                    if (ProjectSettings.getMapData("weixinServerInfo").get("qyappid")!=null) {
                        List<QYAccessToken> qyappidList = new ArrayList<>();
                        qyappidList.add(new QYAccessToken(ProjectSettings.getMapData("weixinServerInfo").get("qyappid").toString(), ProjectSettings.getMapData("weixinServerInfo").get("qyappSecret").toString()));
                        QYAccessToken.updateQYAccessToken(qyappidList);
                    }
                    //清理 扫码登录垃圾数据
                    AccessTokenServlet as = new AccessTokenServlet();
                    for (Map.Entry<String, Object> entry :((Map<String,Object>)as.getServletContext()).entrySet()) {
                        if (entry.getKey().startsWith("uuid")){
                            ((Map<String, Object>) as.getServletContext()).remove(entry.getKey());
                        }
                    }
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
