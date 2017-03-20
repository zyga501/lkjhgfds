package com.wlfg.action;


import com.wlfg.database.MenuTree;
import com.wlfg.database.esf.HouseInfo;
import com.wlfg.utils.RemoteInfo;
import com.wlfg.utils.StringsTools;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utils.ProjectSettings;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EsfAction extends AjaxActionSupport {


    public String  addHouse() {
        Map<String, String> param =new HashMap<>();
        List lh = HouseInfo.getUncheckHouseInfo("1");
        if (lh.size()>5) {
            param.put("msg","请先确认之前的5条信息审核通过再申报");
            return AjaxActionComplete(false,param);
        }
        param = StringsTools.serializeToMAp(getParameter("ExtData").toString());
        System.out.println(param.get("xq"));
        HouseInfo houseInfo = null;
        try {
            houseInfo = HouseInfo.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            org.apache.commons.beanutils.BeanUtils.populate(houseInfo, param);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(houseInfo.getCx());
        lh = HouseInfo.getUnpostHouseInfo("1");
        if (lh.size()>0){
            HouseInfo.delUnpostHouseInfo("1");
        }
        houseInfo.setZid("1");
        houseInfo.setHid(HouseInfo.getSeq("esf_houseinfo_gzk")+"_"+houseInfo.getZid());
        houseInfo.setStatus("0");
        houseInfo.setSjdate((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
        HouseInfo.insertHouseInfo(houseInfo);
        return AjaxActionComplete(true);
    }
}

