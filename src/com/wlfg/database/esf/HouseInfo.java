package com.wlfg.database.esf;

import com.wlfg.database.Database;

import java.util.List;
import java.util.Map;

public class HouseInfo {
    public static List<HouseInfo> getUncheckHouseInfo(Map map) {
        String statement = "com.wlfg.database.esf.mapping.houseinfo.getUncheckHouseInfo";
        return Database.Instance().selectList(statement,map);
    }
    public static List<HouseInfo> getUnpostHouseInfo(Map map) {
        String statement = "com.wlfg.database.esf.mapping.houseinfo.getUnpostHouseInfo";
        return Database.Instance().selectList(statement,map);
    }
    public static List<HouseInfo> getOnlineHouseInfo(Map map) {
        String statement = "com.wlfg.database.esf.mapping.houseinfo.getOnlineHouseInfo";
        return Database.Instance().selectList(statement,map);
    }
    public static List<HouseInfo> getOfflineHouseInfo(Map map) {
        String statement = "com.wlfg.database.esf.mapping.houseinfo.getOfflineHouseInfo";
        return Database.Instance().selectList(statement,map);
    }
    public static List<Map> getUncheckList(Map map) {
        String statement = "com.wlfg.database.esf.mapping.houseinfo.getUncheckList";
        return Database.Instance().selectList(statement,map);
    }
    public static Boolean PostHouseInfo(Map map) {
        String statement = "com.wlfg.database.esf.mapping.houseinfo.PostHouseInfo";
        return Database.Instance().insert(statement,map)==1;
    }

    public static Boolean insertHouseInfo(HouseInfo houseinfo) {
        String statement = "com.wlfg.database.esf.mapping.houseinfo.insertHouseInfo";
        return Database.Instance().insert(statement,houseinfo)==1;
    }
    public static Boolean checkHouseInfo(String map) {
        String statement = "com.wlfg.database.esf.mapping.houseinfo.checkHouseInfo";
        return Database.Instance().insert(statement,map)==1;
    }
    public static String getSeq(String  tabname) {
        String statement = "com.wlfg.database.esf.mapping.houseinfo.getSeq";
        return Database.Instance().selectOne(statement,tabname);
    }
    public static Boolean delUnpostHouseInfo(String  zid) {
        String statement = "com.wlfg.database.esf.mapping.houseinfo.delUnpostHouseInfo";
        return Database.Instance().delete(statement,zid)>0;
    }
    public static Boolean delHouseInfo(String  zid) {
        String statement = "com.wlfg.database.esf.mapping.houseinfo.delHouseInfo";
        return Database.Instance().delete(statement,zid)>0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getSsq() {
        return ssq;
    }

    public void setSsq(String ssq) {
        this.ssq = ssq;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getFwjg() {
        return fwjg;
    }

    public void setFwjg(String fwjg) {
        this.fwjg = fwjg;
    }

    public String getHx_s() {
        return hx_s;
    }

    public void setHx_s(String hx_s) {
        this.hx_s = hx_s;
    }

    public String getHx_t() {
        return hx_t;
    }

    public void setHx_t(String hx_t) {
        this.hx_t = hx_t;
    }

    public String getHx_w() {
        return hx_w;
    }

    public void setHx_w(String hx_w) {
        this.hx_w = hx_w;
    }

    public String getJzmj() {
        return jzmj;
    }

    public void setJzmj(String jzmj) {
        this.jzmj = jzmj;
    }

    public String getZcs() {
        return zcs;
    }

    public void setZcs(String zcs) {
        this.zcs = zcs;
    }

    public String getSzc() {
        return szc;
    }

    public void setSzc(String szc) {
        this.szc = szc;
    }

    public String getCx() {
        return cx;
    }

    public void setCx(String cx) {
        this.cx = cx;
    }

    public String getJcnf() {
        return jcnf;
    }

    public void setJcnf(String jcnf) {
        this.jcnf = jcnf;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getYt() {
        return yt;
    }

    public void setYt(String yt) {
        this.yt = yt;
    }

    public String getSyqx() {
        return syqx;
    }

    public void setSyqx(String syqx) {
        this.syqx = syqx;
    }

    public String getTdxz() {
        return tdxz;
    }

    public void setTdxz(String tdxz) {
        this.tdxz = tdxz;
    }

    public String getCqz() {
        return cqz;
    }

    public void setCqz(String cqz) {
        this.cqz = cqz;
    }

    public String getXjdate() {
        return xjdate;
    }

    public void setXjdate(String xjdate) {
        this.xjdate = xjdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSjdate() {
        return sjdate;
    }

    public void setSjdate(String sjdate) {
        this.sjdate = sjdate;
    }

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    private long id;
    private String hid;
    private String ssq;
    private String xq;
    private String fwjg;
    private String hx_s;
    private String hx_t;
    private String hx_w;
    private String jzmj;
    private String zcs;
    private String szc;
    private String cx;
    private String jcnf;
    private String lx;
    private String yt;
    private String syqx;
    private String tdxz;
    private String cqz;
    private String status;
    private String openid;
    private String sjdate;
    private String xjdate;
    private String zid;
    private String jg;


}
