package com.berlski.tool.test;

import java.io.Serializable;

/**
 * Created by Berlski on 2017/6/27.
 */

public class Department implements Serializable{

    //private String choose = "";
    //private String pdeptId = "";

    private String name;                      //"客服部",
    private String id;                      //"b63b3e89032b49babf54945a1194d7f7",

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
