package com.wlfg.action;

import com.wlfg.weixin.api.JsapiTicket;
import com.wlfg.weixin.api.QYAccessToken;
import com.wlfg.weixin.api.QYUserId;
import com.wlfg.weixin.api.QYUserIdInfo;
import com.wlfg.weixin.utils.configSignature;
import org.xml.sax.SAXException;
import utils.ProjectSettings;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class QyAction extends AjaxActionSupport {

    public void fetchWxCode()  {
        try {
            String appid = ProjectSettings.getMapData("weixinServerInfo").get("qyappid").toString();
            String redirect_uri = getRequest().getScheme() + "://" + getRequest().getServerName() + getRequest().getContextPath() + "/QYAct!fetchWxOpenid";
            String fetchOpenidUri = String.format("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +
                            "%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=%s#wechat_redirect",
                    appid, redirect_uri, null!=getParameter("redirect_url")?getParameter("redirect_url").toString():"http://www.baidu.com");
            getResponse().sendRedirect(fetchOpenidUri);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void fetchWxOpenid()  {
        try {
            String appid = ProjectSettings.getMapData("weixinServerInfo").get("qyappid").toString();
            //String appsecret = ProjectSettings.getMapData("weixinServerInfo").get("qyappSecret").toString();
            QYUserId qyUserId = new QYUserId(QYAccessToken.getQYAccessToken(appid), getParameter("code").toString());
            if (qyUserId.getRequest()) {
                getRequest().getSession().setAttribute("openid", qyUserId.getUserId());
                getResponse().sendRedirect(getParameter("state").toString() );
            }
        }
        catch (Exception e){

            e.printStackTrace();
            StringBuffer sb = new StringBuffer();
            StackTraceElement[] stackArray = e.getStackTrace();
            for (int i = 0; i < stackArray.length; i++) {
                StackTraceElement element = stackArray[i];
                sb.append(element.toString() + "\n");
            }
            e.printStackTrace();
        }
    }

    public String bingo() throws IOException {
            setParameter("redirect_url", "QYAct!bingo2");
            return "fetchWxCode";
    }

    public String bingo2() throws IOException {
        if (null==getRequest().getSession().getAttribute("openid")) {
            setParameter("redirect_url", "QYAct!bingo2");
            return "fetchWxCode";
        }
        Object uname = null;
        try {
            ProjectSettings.reloadRes();
            uname = ProjectSettings.getMapData("userinfo").get(getRequest().getSession().getAttribute("openid").toString());
        }
        catch (Exception e){

        }
        if (null==uname){
            setParameter("redirect_url", "QYAct!bingo");
            //getResponse().sendRedirect("regPage.jsp");
            return "regPage";
        }
        setParameter("u",new String (uname.toString().getBytes("utf-8"),"ISO8859-1"));
        return "daka";
    }

    public String reg() throws IOException, TransformerException, SAXException, ParserConfigurationException {
        if (null==getRequest().getSession().getAttribute("openid")) {
            setParameter("redirect_url", "QYAct!reg");
            getResponse().sendRedirect("QYAct!fetchWxCode");
        }
    try {
        for (Map.Entry<String, Object> entry : ProjectSettings.getMapData("userinfo").entrySet()) {
            if (entry.getValue().toString().contentEquals(new String (getParameter("uname").toString().getBytes("ISO8859-1"),"utf-8")))
                return AjaxActionComplete(false);
        }
    }
    catch (Exception e){

    }
        return AjaxActionComplete(ProjectSettings.setData("userinfo/"+getRequest().getSession().getAttribute("openid"),new String (getParameter("uname").toString().getBytes("iso-8859-1"),"utf-8")));
    }

    public String getSignPackage() throws Exception {
        Map<String,Object> resultMap = new HashMap<>();
        String  appidstr = ProjectSettings.getMapData("weixinServerInfo").get("qyappid").toString();
        resultMap.put("appId", appidstr);
        String nonceiStr = Long.toString(System.currentTimeMillis() / 1000);
        resultMap.put("timeStamp", nonceiStr);
        resultMap.put("nonceStr", nonceiStr);
        String sginstr = configSignature.sign(JsapiTicket.getJsApiTicket(appidstr),nonceiStr,nonceiStr, getRequest().getRequestURL().toString());
        resultMap.put("signature", sginstr);
        return AjaxActionComplete(resultMap);
    }

    /*循环验证*/
    public String pclogin(){
        String  uuid = getParameter("uuid").toString();
        Date dt =new Date();
        while  ((new Date()).getTime()-dt.getTime()<50000){
            if ((null!=getApplication().get(uuid)) && ((boolean)getApplication().get(uuid))) {
                Map attr = (Map) getApplication().get(getParameter("uuid") + "userinfo");
                getRequest().getSession().setAttribute("userinfo",attr);
                getApplication().remove(uuid);
                getApplication().remove(uuid + "userinfo");
                Map map = new HashMap<>();
                map.put("href","../esf/addhouse.jsp");
                return AjaxActionComplete(true, map);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return AjaxActionComplete (false);
    }
    public String mkpclogin(){
        Map map = new HashMap<>();
        map.put("uuid", UUID.randomUUID().toString());
        return AjaxActionComplete (map);
    }
/*中介登录*/
    public String sdfgk() throws IOException {
        setParameter("redirect_url", "QYAct!checkzj?uuid="+getParameter("uuid").toString());
    return "fetchWxCode";
    }

    public void checkzj() throws IOException {
        if (null!=getRequest().getSession().getAttribute("openid") && (null!=getParameter("uuid"))) {
            getApplication().put(getParameter("uuid"),true);
            try {
            QYUserIdInfo qyUserIdInfo = new QYUserIdInfo(QYAccessToken.getQYAccessToken(ProjectSettings.getMapData("weixinServerInfo").get("qyappid").toString()),
                    getRequest().getSession().getAttribute("openid").toString());
            if (qyUserIdInfo.getRequest()) {
                getApplication().put(getParameter("uuid")+"userinfo",qyUserIdInfo.getMsgMap());
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
