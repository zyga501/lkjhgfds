package com.wlfg.database;


import java.util.List;
import java.util.Map;

public class CheckWork {

    public static List<CheckWork> getAllData(Map map) {
        String statement = "com.wlfg.database.mapping.checkwork.getAllData";
        return Database.Instance().selectList(statement,map);
    }

    public static boolean insertLog(CheckWork checkWork) {
        String statement = "com.wlfg.database.mapping.checkwork.insertLog";
        return Database.Instance().insert(statement, checkWork)==1;
    }
    private long id_;
    private String lx_;
    private String ly_;
    private String uname;
    private String signtime;

    public long getId() {
        return id_;
    }

    public void setId(long id_) {
        this.id_ = id_;
    }

    public String getLx() {
        return lx_;
    }

    public void setLx(String lx_) {
        this.lx_ = lx_;
    }

    public String getLy() {
        return ly_;
    }

    public void setLy(String ly_) {
        this.ly_ = ly_;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSigntime() {
        return signtime;
    }

    public void setSigntime(String signtime) {
        this.signtime = signtime;
    }
}
