package com.wlfg.action;


import com.wlfg.database.esf.AgencyInfo;
import com.wlfg.database.esf.HouseInfo;
import com.wlfg.utils.StringsTools;
import com.wlfg.weixin.api.QYAccessToken;
import com.wlfg.weixin.api.QYUserIdInfo;
import com.wlfg.weixin.utils.QYMessageControl;
import sun.misc.BASE64Encoder;
import utils.ImageZipUtil;
import utils.ProjectSettings;
import utils.WaterMarkUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EsfAction extends AjaxActionSupport {

    public String manageList(){

        if (null==getRequest().getSession().getAttribute("openid")) {
            setParameter("redirect_url", "esf/manageList!esf");
            return "fetchWxCode";
        }
        else {
            try {
                QYUserIdInfo qyUserIdInfo = new QYUserIdInfo(QYAccessToken.getQYAccessToken(ProjectSettings.getMapData("weixinServerInfo").get("qyappid").toString()),
                        getRequest().getSession().getAttribute("openid").toString());
                if (qyUserIdInfo.getRequest()) {
                    getRequest().getSession().setAttribute("userinfo", qyUserIdInfo.getMsgMap());
                }
            } catch (Exception e) {
            }
            Map map = AgencyInfo.fetchAM(getRequest().getSession().getAttribute("openid").toString());
            if (null!=map){
                return "checkList";
            }
            AgencyInfo agencyInfo = AgencyInfo.fetchAgency( getRequest().getSession().getAttribute("openid").toString());
            if (null!=agencyInfo){
                return "agencyHouseList";
            }
        }
        return "page404";
    }

    public String addHousePage(){
        if (null==getRequest().getSession().getAttribute("openid")) {
            setParameter("redirect_url", "esf/addHousePage!esf");
            return "fetchWxCode";
        }
        try {
            QYUserIdInfo qyUserIdInfo = new QYUserIdInfo(QYAccessToken.getQYAccessToken(ProjectSettings.getMapData("weixinServerInfo").get("qyappid").toString()),
                    getRequest().getSession().getAttribute("openid").toString());
            if (qyUserIdInfo.getRequest())
            getRequest().getSession().setAttribute("userinfo",qyUserIdInfo.getMsgMap());
        } catch (Exception e) {
        }
        AgencyInfo agencyInfo = AgencyInfo.fetchAgency(getRequest().getSession().getAttribute("openid").toString());
        if (null!=agencyInfo){
            return "addHousePage";
        }
        return "page404";
    }

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
        AgencyInfo agencyInfo = AgencyInfo.fetchAgency(((Map) getRequest().getSession().getAttribute("userinfo")).get("userid").toString());
        if (lh.size()>=5) {
            param.put("msg","请和房管处联系，微信信息未挂接");
            return AjaxActionComplete(false,param);
        }
        param = StringsTools.serializeToMAp(getParameter("ExtData").toString());
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
            HouseInfo.delUnpostHouseInfo(String.valueOf(agencyInfo.getZid()));
        }
        houseInfo.setHid(HouseInfo.getSeq("esf_houseinfo_gzk")+"_"+String.valueOf(agencyInfo.getZid()));
        houseInfo.setStatus("0");
        houseInfo.setOpenid(((Map) getRequest().getSession().getAttribute("userinfo")).get("userid").toString());
        houseInfo.setSjdate((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
        houseInfo.setZid(String.valueOf(agencyInfo.getZid()));
        HouseInfo.insertHouseInfo(houseInfo);
        File[] f = (File[]) getParameterArr("xcz");
        if (null==f){
            param.put("msg","请上传图片！");
            return AjaxActionComplete(false,param);
        }
        for (int i=0;i<f.length;i++) {
            if (f[i].length()/1024>500) {
                ImageZipUtil.zipImageFile(f[i], new File(ProjectSettings.getMapData("esf").get("uncheckPath").toString() + houseInfo.getHid() +
                        "_" + String.valueOf(i) + ".jpg"), 888, 888, 0.7f);
                WaterMarkUtils.markPic(ProjectSettings.getMapData("esf").get("uncheckPath").toString() + houseInfo.getHid() +
                            "_" + String.valueOf(i) + ".jpg",
                        ProjectSettings.getMapData("esf").get("uncheckPath").toString() + houseInfo.getHid() +
                            "_" + String.valueOf(i) + ".jpg",agencyInfo.getAgency());
            }
            else {
                WaterMarkUtils.markPic(f[i].getAbsolutePath(),ProjectSettings.getMapData("esf").get("uncheckPath").toString() + houseInfo.getHid() +
                        "_" + String.valueOf(i) + ".jpg",agencyInfo.getAgency());
            }
        }
        param.clear();
        param.put("hid",houseInfo.getHid());
        return AjaxActionComplete(true,param);
    }

    public String  addHouseSH(){
        try {
            String hid = getParameter("hid").toString();
            List<HouseInfo> lhouseInfo = HouseInfo.getUnpostHouseInfo(new HashMap() {{
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
                AgencyInfo agencyInfo = AgencyInfo.fetchAgency(((Map) getRequest().getSession().getAttribute("userinfo")).get("userid").toString());
                File[] f = (File[]) getParameterArr("fcz");
                for (int i = 0; i < f.length; i++) {
                    if (f[i].length()/1024>500) {
                        ImageZipUtil.zipImageFile(f[i], new File(ProjectSettings.getMapData("esf").get("uncheckPath").toString() + lhouseInfo.get(0).getHid() +
                                "_fcz_" + String.valueOf(i) + ".jpg"), 888, 888, 0.7f);
                        WaterMarkUtils.markPic(ProjectSettings.getMapData("esf").get("uncheckPath").toString() + lhouseInfo.get(0).getHid() +
                                        "_fcz_" + String.valueOf(i) + ".jpg",
                                ProjectSettings.getMapData("esf").get("uncheckPath").toString() + lhouseInfo.get(0).getHid() +
                                        "_fcz_" + String.valueOf(i) + ".jpg",agencyInfo.getAgency());
                    }
                    else {
                        WaterMarkUtils.markPic(f[i].getAbsolutePath(),ProjectSettings.getMapData("esf").get("uncheckPath").toString() + lhouseInfo.get(0).getHid() +
                                "_fcz_" + String.valueOf(i) + ".jpg",agencyInfo.getAgency());
                    }
                }
            }
            return AjaxActionComplete(true);
        }catch (Exception e){
            return AjaxActionComplete(false);
        }
    }

    public String  uncheckList(){
        Map  ammap = AgencyInfo.fetchAM( getRequest().getSession().getAttribute("openid").toString());
        if (null==ammap){
            return AjaxActionComplete();
        }
        else {
            List<Map> houseInfoList = HouseInfo.getUncheckList(null);
            return AjaxActionComplete(houseInfoList);
        }
    }

    public String checkHouseinfo(){
        Map  ammap = AgencyInfo.fetchAM( getRequest().getSession().getAttribute("openid").toString());
        if (null==ammap){
            return AjaxActionComplete();
        }
        else
        return AjaxActionComplete(HouseInfo.checkHouseInfo(getParameter("hid").toString()));
    }

    public String checkPic() throws IOException {
        String hid = getParameter("hid").toString();
        List<HouseInfo> lhouseInfo =  HouseInfo.getUncheckHouseInfo(new HashMap() {{
            put("hid",hid);}});
        if (null==lhouseInfo || lhouseInfo.get(0).getOpenid().compareTo(((Map) getRequest().getSession().getAttribute("userinfo")).get("userid").toString())!=0) {
            Map ammap = AgencyInfo.fetchAM(getRequest().getSession().getAttribute("openid").toString());
            if (null == ammap) {
                return AjaxActionComplete();
            }
        }
//        String hid = getParameter("hid").toString();
        List m= new ArrayList<>();
        for (int i=0;i<10;i++){
           File  f =  new File(ProjectSettings.getMapData("esf").get("uncheckPath").toString() + hid +
                    "_fcz_" + String.valueOf(i) + ".jpg");
            if (f.exists()){
                FileInputStream inputFile = null;
                inputFile = new FileInputStream(f);
                byte[] buffer = new byte[(int) f.length()];
                inputFile.read(buffer);
                inputFile.close();
                m.add(new BASE64Encoder().encode(buffer));
            }
        }
        return AjaxActionComplete(m);
    }

    public String xczPic() throws IOException {
        String hid = getParameter("hid").toString();
        List<HouseInfo> lhouseInfo =  HouseInfo.getUncheckHouseInfo(new HashMap() {{
            put("hid",hid);}});
        if (null==lhouseInfo || lhouseInfo.get(0).getOpenid().compareTo(((Map) getRequest().getSession().getAttribute("userinfo")).get("userid").toString())!=0){
            return AjaxActionComplete();
        }
        List m= new ArrayList<>();
        for (int i=0;i<10;i++){
            File  f =  new File(ProjectSettings.getMapData("esf").get("uncheckPath").toString() + hid +"_"+
                    String.valueOf(i) + ".jpg");
            if (f.exists()){
                FileInputStream inputFile = null;
                inputFile = new FileInputStream(f);
                byte[] buffer = new byte[(int) f.length()];
                inputFile.read(buffer);
                inputFile.close();
                m.add(new BASE64Encoder().encode(buffer));
            }
        }
        return AjaxActionComplete(m);
    }
    public String checkedList(){
        Map  ammap = AgencyInfo.fetchAM( getRequest().getSession().getAttribute("openid").toString());
        if (null==ammap){
            return AjaxActionComplete(false);
        }
        String[] hidarr = getParameter("hid").toString().split(",");
        for (String hid :hidarr) {
            if ("1".compareTo(getParameter("ok").toString())==0) {
                if (HouseInfo.checkHouseInfo(hid)){
                    HouseInfo.delHouseInfo(hid);
                }
                for (int i=0;i<20;i++) {
                    File f = new File(ProjectSettings.getMapData("esf").get("uncheckPath").toString() + hid +
                             String.valueOf(i) + ".jpg");
                    if (f.exists()){
                        f.renameTo(new File(ProjectSettings.getMapData("esf").get("Picpath").toString() + hid +
                                 String.valueOf(i) + ".jpg"));
                    }
                }
                for (int i=0;i<10;i++) {
                    File f = new File(ProjectSettings.getMapData("esf").get("uncheckPath").toString() + hid +
                            "_fcz_" + String.valueOf(i) + ".jpg");
                    if (f.exists()){
                        f.renameTo(new File(ProjectSettings.getMapData("esf").get("Picpath").toString() + hid +
                                "_fcz_" + String.valueOf(i) + ".jpg"));
                    }
                }
            }
            else{
                HouseInfo houseInfo = HouseInfo.getUncheckHouseInfo(new HashMap() {{ put("hid", hid);}}).get(0);
                if  (HouseInfo.delHouseInfo(hid))
                    for (int i=0;i<20;i++) {
                        File f = new File(ProjectSettings.getMapData("esf").get("uncheckPath").toString() + hid +
                                String.valueOf(i) + ".jpg");
                        if (f.exists()){
                           f.delete();
                        }
                    }
                for (int i=0;i<10;i++) {
                    File f = new File(ProjectSettings.getMapData("esf").get("uncheckPath").toString() + hid +
                            "_fcz_" + String.valueOf(i) + ".jpg");
                    if (f.exists()){
                        f.delete();
                    }
                }
                    try {
                        QYMessageControl.sendMsg(houseInfo.getOpenid(),"由于未通过审核，房源被删除："+houseInfo.getXq()+
                                "-"+houseInfo.getSyqx(),ProjectSettings.getMapData("esf").get("agency_agentid").toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
        }
        return AjaxActionComplete(true);
    }

    public  String uncheckHouseinfo(){
        AgencyInfo agencyInfo = AgencyInfo.fetchAgency(((Map) getRequest().getSession().getAttribute("userinfo")).get("userid").toString());
        if (null==agencyInfo){
            return AjaxActionComplete();
        }
        else
            return AjaxActionComplete(HouseInfo.getUncheckHouseInfo(new HashMap() {{
                put("openid",agencyInfo.getUserid());}}));
    }
}

