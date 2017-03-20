package com.wlfg.action;


import com.wlfg.database.MenuTree;
import com.wlfg.utils.RemoteInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.ProjectSettings;

import java.io.IOException;
import java.util.*;
import java.util.function.BooleanSupplier;

public class HtmlAction extends AjaxActionSupport {

    public String indexs(){
        Map project = new HashMap<>();
        project.put("title","你好中国");
        project.put("address","哼胡中路99号");
        project.put("contacttel","05766212250");
        project.put("bah","浙备125411号");//navpageList
        getRequest().setAttribute("project", project);

        try {
            List<MenuTree> navpageList = MenuTree.getAllMenuNode();
//            Map menu = new HashMap<>();
//            menu.put("href", "/mainland.jsp");
//            menu.put("label", "首  页");
//            navpageList.add(menu);
//            Map menu1 = new HashMap<>();
//            menu1.put("href", "/website.jsp");
//            menu1.put("label", "交易管理");
//            navpageList.add(menu1);
            getRequest().setAttribute("navpageList", navpageList);
            return "mainpage";
        }
        catch (Exception e){
            e.printStackTrace();
            return "mainpage";
        }
    }

    public String getmenu(){
        Map project = new HashMap<>();
        List<MenuTree> lm = MenuTree.getAllMenuNode();
        project.put("menulist",lm);
        return AjaxActionComplete(true,project);
    }

    public String setmenu(){
        String  mlist = getParameter("content").toString();
        System.out.println(mlist);
        JSONObject jb=new JSONObject();
        JSONArray jarray=JSONArray.fromObject(mlist);
        MenuTree menuTree = new MenuTree();
        Iterator<Object> it = jarray.iterator();
        int order = 1;
        while (it.hasNext()) {
            JSONObject ob = (JSONObject) it.next();
            if ( ob.getString("id").equals("0")){
                menuTree.setMenuname(ob.getString("menuname"));
                menuTree.setMenuorder(order);
                MenuTree.insertMenuNode(menuTree);
            }else{
                menuTree.setMenuname(ob.getString("menuname"));
                menuTree.setMenuorder(order);
                menuTree.setId(Integer.parseInt(ob.getString("id")));
                MenuTree.updateMenuNode(menuTree);
            }
            order +=1;
        }
        return AjaxActionComplete(true);
    }

    public Boolean prtIp(){
        try {
            String s = RemoteInfo.getIpAddress(getRequest());
            return  (ProjectSettings.getData("hostip").equals(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}

