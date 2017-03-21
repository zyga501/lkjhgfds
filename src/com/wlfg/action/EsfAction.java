package com.wlfg.action;


import com.wlfg.database.esf.HouseInfo;
import com.wlfg.utils.StringsTools;
import utils.ProjectSettings;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EsfAction extends AjaxActionSupport {


    public String  addHouse() {
        Map<String, String> param =new HashMap<>();
        if (null==getRequest().getSession().getAttribute("userinfo")){
            param.put("msg","请先登录！");
            return AjaxActionComplete(false,param);
        }
        List lh = HouseInfo.getUncheckHouseInfo(new HashMap(){{put("openid",((Map) getRequest().getSession().getAttribute("userinfo")).get("userid"));}});
        if (lh.size()>=5) {
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
        lh = HouseInfo.getUnpostHouseInfo(new HashMap(){{put("openid", ((Map) getRequest().getSession().getAttribute("userinfo")).get("userid"));}});
        if (lh.size()>0){
            HouseInfo.delUnpostHouseInfo("1");
        }
        houseInfo.setHid(HouseInfo.getSeq("esf_houseinfo_gzk")+"_"+houseInfo.getZid());
        houseInfo.setStatus("0");
        houseInfo.setOpenid(((Map) getRequest().getSession().getAttribute("userinfo")).get("userid").toString());
        houseInfo.setSjdate((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
        HouseInfo.insertHouseInfo(houseInfo);
        File[] f = (File[]) getParameterArr("xcz");
        if (null==f){
            param.put("msg","请上传图片！");
            return AjaxActionComplete(false,param);
        }
        for (int i=0;i<f.length;i++) {
            f[i].renameTo(new File(ProjectSettings.getMapData("esf").get("uncheckPath").toString() +houseInfo.getHid()+
                    "_"+String.valueOf(i)+".jpg"));
        }
        param.clear();
        param.put("hid",houseInfo.getHid());
        return AjaxActionComplete(true,param);
    }

    public String  addHouseSH(){
        try {
            String hid = getParameter("hid").toString();
            List<HouseInfo> lhouseInfo = HouseInfo.getUncheckHouseInfo(new HashMap() {{
                put("hid", hid);
                put("openid",
                        ((Map) getRequest().getSession().getAttribute("userinfo")).get("userid"));
            }});
            if ((null != hid) && (lhouseInfo.size() == 1)) {
                HouseInfo.PostHouseInfo(new HashMap() {{
                    put("hid", hid);
                    put("openid",((Map) getRequest().getSession().getAttribute("userinfo")).get("userid"));
                    put("cqz", getParameter("cqz"));
                }});
                File[] f = (File[]) getParameterArr("fcz");
                for (int i = 0; i < f.length; i++) {
                    f[i].renameTo(new File(ProjectSettings.getMapData("esf").get("uncheckPath").toString() + lhouseInfo.get(0).getHid() +
                            "_fcz_" + String.valueOf(i) + ".jpg"));
                }
            }
            return AjaxActionComplete(true);
        }catch (Exception e){
            return AjaxActionComplete(false);
        }
    }

    public String  uncheckList(){
        List<Map> houseInfoList = HouseInfo.getUncheckList(null);
        return AjaxActionComplete(houseInfoList);
    }

    public String checkHouseinfo(){
        return AjaxActionComplete(HouseInfo.checkHouseInfo(new HashMap(){{put("hid",getParameter("hid").toString());}}));
    }
}

