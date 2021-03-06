package com.wlfg.action;

import com.wlfg.database.CheckWork;
import com.wlfg.services.webgetpost;
import com.wlfg.weixin.api.JsapiTicket;
import com.wlfg.weixin.api.QYAccessToken;
import com.wlfg.weixin.api.QYUserId;
import com.wlfg.weixin.api.QYUserIdInfo;
import com.wlfg.weixin.utils.configSignature;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.xml.sax.SAXException;
import utils.ProjectSettings;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.wlfg.services.TencentMap.getDistance;


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
        try {
            QYUserIdInfo qyUserIdInfo = new QYUserIdInfo(QYAccessToken.getQYAccessToken(ProjectSettings.getMapData("weixinServerInfo").get("qyappid").toString()),
                    getRequest().getSession().getAttribute("openid").toString());
            if (qyUserIdInfo.getRequest()) {
                setParameter("u",new String(qyUserIdInfo.getMsgMap().get("name").toString().getBytes("utf-8"),"ISO8859-1"));
                return "daka";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error!";
    }

    public String gpsinfo(){
        if (null==getRequest().getSession().getAttribute("openid")) {
            setParameter("redirect_url", "QYAct!gpsinfo");
            return "fetchWxCode";
        }
        return "getLocation";
    }
    public String getgps() throws IOException {

        float dis = getDistance(getParameter("lx").toString(),getParameter("ly").toString(),"28.384680","121.383991");
        if (dis>200 || dis<0){
            Map map = new HashMap();
            map.put("rt","距离太远，请就近打卡！");
            return AjaxActionComplete(map);
        }
        CheckWork ck= new CheckWork();
        ck.setLx(getParameter("lx").toString());
        ck.setLy(getParameter("ly").toString());
        ck.setSigntime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
        try {
            QYUserIdInfo qyUserIdInfo = new QYUserIdInfo(QYAccessToken.getQYAccessToken(ProjectSettings.getMapData("weixinServerInfo").get("qyappid").toString()),
                    getRequest().getSession().getAttribute("openid").toString());
            if (qyUserIdInfo.getRequest()) {
                setParameter("u",new String(qyUserIdInfo.getMsgMap().get("name").toString().getBytes("utf-8"),"ISO8859-1"));
                ck.setUname(qyUserIdInfo.getMsgMap().get("name").toString());
                CheckWork.insertLog(ck);
                return "daka";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error!";
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
        String sginstr = configSignature.sign(JsapiTicket.getJsApiTicket(appidstr),nonceiStr,nonceiStr,
                getRequest().getScheme() + "://" + getRequest().getServerName()+getRequest().getContextPath()+"/QYAct!gpsinfo");
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

    public String  getsignpos(){
        try {
            QYUserIdInfo qyUserIdInfo = new QYUserIdInfo(QYAccessToken.getQYAccessToken(ProjectSettings.getMapData("weixinServerInfo").get("qyappid").toString()),
                    getRequest().getSession().getAttribute("openid").toString());
            if (qyUserIdInfo.getRequest()) {
                List<CheckWork> lc =  CheckWork.getAllData(new HashMap() {{
                    put("uname",qyUserIdInfo.getMsgMap().get("name").toString());}});
                String posarr="" ;
                for (CheckWork  c:lc) {
                    posarr+=c.getLx()+","+c.getLy()+";";
                }
                posarr = posarr.substring(0,posarr.length()-1);
                String responsestr = webgetpost.geturl("http://apis.map.qq.com/ws/coord/v1/translate?locations="+posarr +
                               "&type=1&key=ZO4BZ-BZXK4-JN2UU-DIZMB-OWDS5-UKBVD");
                JSONObject jsonParse =JSONObject.fromObject(responsestr);
                List<String> lp = new ArrayList<>();
                if (jsonParse.containsKey("locations")) {
                    lp = JSONArray.toList(JSONArray.fromObject(jsonParse.get("locations")));
                }
                return AjaxActionComplete(lp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "page404";
    }
}
