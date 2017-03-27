package com.wlfg.database.esf;

import com.wlfg.database.Database;

import java.util.List;
import java.util.Map;

public class AgencyInfo {
    public static AgencyInfo fetchAgency (String userid){
        String statement = "com.wlfg.database.esf.mapping.agencyinfo.fetchAgency";
        return Database.Instance().selectOne(statement,userid);
    }
    public static AgencyInfo fetchAgencyByName (String agency){
        String statement = "com.wlfg.database.esf.mapping.agencyinfo.fetchAgencyByName";
        return Database.Instance().selectOne(statement,agency);
    }

    public static Map fetchAM (String userid){
        String statement = "com.wlfg.database.esf.mapping.agencyinfo.fetchAM";
        return Database.Instance().selectOne(statement,userid);
    }
    private int zid;
    private String agency;
    private String adress;
    private String tel;
    private String userid;

    public int getZid() {
        return zid;
    }

    public void setZid(int zid) {
        this.zid = zid;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
