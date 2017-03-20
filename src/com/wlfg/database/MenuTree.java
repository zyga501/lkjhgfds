package com.wlfg.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuTree {

    public static List<MenuTree> getAllMenuNode() {
        String statement = "com.wlfg.database.mapping.menutree.getAllMenuNode";
        return Database.Instance().selectList(statement);
    }

    public static List<MenuTree> getMenuNodeByPreId(long preid,long roleid) {
        String statement = "com.wlfg.database.mapping.menutree.getMenuNodeByPreId";
        Map<String, Object> param=new HashMap<String, Object>();
        param.put("preid",preid);
        param.put("roleid",roleid);
        return Database.Instance().selectList(statement, param);
    }
    public static List<MenuTree> getMenuNodeByUid(long uid) {
        String statement = "com.wlfg.database.mapping.menutree.getMenuNodeByUid";
        return Database.Instance().selectList(statement, uid);
    }
    public static boolean insertMenuNode(MenuTree menuTree) {
        String statement = "com.wlfg.database.mapping.menutree.insertMenuNode";
        return Database.Instance().insert(statement, menuTree)==1;
    }
    public static boolean updateMenuNode(MenuTree menuTree) {
        String statement = "com.wlfg.database.mapping.menutree.updateMenuNode";
        return Database.Instance().update(statement, menuTree)==1;
    }

    private long id_;
    private long preid_;
    private String menuname_;
    private String webpath_;
    private String labelico_;
    private long menuorder_;

    public String getLabelico() {
        return labelico_;
    }

    public void setLabelico(String labelico_) {
        this.labelico_ = labelico_;
    }

    public long getId() {
        return id_;
    }

    public void setId(long id_) {
        this.id_ = id_;
    }

    public long getPreid() {
        return preid_;
    }

    public void setPreid(long preid_) {
        this.preid_ = preid_;
    }

    public String getMenuname() {
        return menuname_;
    }

    public void setMenuname(String menuname_) {
        this.menuname_ = menuname_;
    }

    public String getWebpath() {
        return webpath_;
    }

    public void setWebpath(String webpath_) {
        this.webpath_ = webpath_;
    }

    public long getMenuorder() {
        return menuorder_;
    }

    public void setMenuorder(long menuorder_) {
        this.menuorder_ = menuorder_;
    }

}
