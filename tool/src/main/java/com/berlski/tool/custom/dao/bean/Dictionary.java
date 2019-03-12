package com.berlski.tool.custom.dao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;

/**
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 字典包bean类
 * Created by Berlski on 2019/1/22.
 *
 * @Entity 用于标识这是一个需要Greendao帮我们生成代码的bean，GreenDao会映射成sqlite中的一个表
 */
@Entity(//把 id 和 parentId 放在一起作为一个综合主键
        nameInDb = "DICTIONARY_ID",
        indexes = {
                @Index(value = "id DESC, dictionaryType DESC", unique = true)
        })
public class Dictionary {
    /**
     * @Id 标识主键，@Id(autoincrement = true)括号里可以指定是否自增
     * @Property 标识该属性在表中对应的列名（默认不写就是保持一致）
     * @Property(nameInDb = "USERNAME")为该属性映射的列设置一个非默认的名称，默认是将单词大写，用下划线分割单词，如属性名customName对应列名CUSTOM_NAME
     * @Transient 标识该属性将不会映射到表中，也就是没有这列
     * @NotNull 非空
     */

    private String type;        //"",
    private String dictionaryType;        //"",
    private String id;        //"",
    private String value;        //"",
    private String key;        //"",
    private String mark;        //"",
    private String desc;        //"",
    private boolean isCheck;        //"",

    public Dictionary() {
    }

    public Dictionary(String key, String id) {
        this.key = key;
        this.id = id;
    }

    @Generated(hash = 26703346)
    public Dictionary(String type, String dictionaryType, String id, String value, String key, String mark,
            String desc, boolean isCheck) {
        this.type = type;
        this.dictionaryType = dictionaryType;
        this.id = id;
        this.value = value;
        this.key = key;
        this.mark = mark;
        this.desc = desc;
        this.isCheck = isCheck;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key == null ? "" : key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDictionaryType() {
        return this.dictionaryType;
    }

    public void setDictionaryType(String dictionaryType) {
        this.dictionaryType = dictionaryType;
    }

    public boolean getIsCheck() {
        return this.isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }
}