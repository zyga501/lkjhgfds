package com.wlfg.action;

import com.opensymphony.xwork2.ActionContext;
import com.wlfg.services.Httpclientweb;
import com.wlfg.services.webgetpost;
import com.wlfg.utils.StringsTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class serviceAction extends  AjaxActionSupport {
    public String  daka() throws IOException {
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
            Map map = new HashMap();
            map.put("rt",responseString);
            return AjaxActionComplete(map);
//            getResponse().setContentType("text/html;charset=UTF-8");
//            getRequest().setCharacterEncoding("utf-8");
//            PrintWriter pw = getResponse().getWriter();
//            pw.println(responseString);
//            pw.flush();
//            pw.close();
        } catch (Exception e) {
//            ((HttpServletResponse)ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE)).setCharacterEncoding("gbk");
//            PrintWriter pw = ((HttpServletResponse )ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE)).getWriter();
//            pw.println("Error!");
//            pw.flush();
//            pw.close();
            return "page404";
        }
    }

    public String qqlbs() throws IOException {
        String responsestr = webgetpost.geturl(getParameter("gourl").toString());
        JSONObject jsonParse =JSONObject.fromObject(responsestr);
        List<String> lp = new ArrayList<>();
        lp.add(responsestr);
        return AjaxActionComplete(lp);
    }
}
