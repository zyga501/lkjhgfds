package com.wlfg.action;

import com.opensymphony.xwork2.ActionContext;
import com.wlfg.services.Httpclientweb;
import com.wlfg.utils.StringsTools;
import com.wlfg.weixin.utils.configSignature;
import org.apache.struts2.ServletActionContext;
import utils.ProjectSettings;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class serviceAction extends  AjaxActionSupport {
    public void daka() throws IOException {
        try {
            System.out.print(new String (getParameter("u").toString().getBytes("ISO8859-1"),"utf-8"));
            Httpclientweb httpclientweb = new Httpclientweb();
            ActionContext ct= ActionContext.getContext();
            String responseString;
            if (null==getParameter("w") && StringsTools.getWeekOfDate(new Date())>5)
                responseString="周末不打卡！";
            else
                responseString = httpclientweb.dourl((new String (getParameter("u").toString().getBytes("ISO8859-1"),"utf-8")),
                        null==getParameter("p")?"123":getParameter("p").toString());
            //System.out.print(responseString);
            getResponse().setContentType("text/html;charset=UTF-8");
            getRequest().setCharacterEncoding("utf-8");
            PrintWriter pw = getResponse().getWriter();
            String nonceiStr = Long.toString(System.currentTimeMillis() / 1000);
            String  appidstr = ProjectSettings.getMapData("weixinServerInfo").get("appid").toString();
            pw.println(responseString);
//            pw.println("<script src=\"http://res.wx.qq.com/open/js/jweixin-1.1.0.js\"></script>"+
//                    "<script src='../js/jquery/jquery.min.js'></script><script src='../js/wxdojs/wxgps.js'></script><script>var w=new wxjsobj('"+
//                    appidstr+"','"+nonceiStr+
//                    "','"+nonceiStr+"','"+ configSignature.sign(JsapiTicketUtil.getJsApiTicket(appidstr),nonceiStr,nonceiStr, getRequest().getRequestURL().toString())+
//                    "');</script>");
            pw.flush();
            pw.close();
        } catch (Exception e) {
            ((HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE)).setCharacterEncoding("gbk");
            PrintWriter pw = ((HttpServletResponse )ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE)).getWriter();
            pw.println("Error!");
            pw.flush();
            pw.close();
        }
    }
}
