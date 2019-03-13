package com.berlski.tool.test;

import java.io.Serializable;

/**
 * Created by Berlski on 2017/11/27.
 */

public class Role implements Serializable {
    private String name;
    private String id;

    public Role() {
    }

    public Role(String name, String id) {
        this.name = name;
        this.id = id;
    }

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
