package com.berlski.tool.test;

/**
 * Copyright (SaaS 技术团队)
 * FileName: ItemBean
 * Author: Berlski
 * Date: 2019/9/20 16:27
 * Description: ${DESCRIPTION}
 */
public class ItemBean {

    private String name;
    private Class aClass;

    public ItemBean (String name,Class aClass){
        this.name = name;
        this.aClass = aClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }
}
