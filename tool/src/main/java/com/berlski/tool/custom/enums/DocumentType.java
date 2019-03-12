package com.berlski.tool.custom.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berlski on 2018/1/14.
 */
public enum DocumentType {

    /**
     * 身份证
     */
    ID_CARD("1", "身份证"),//("ID_card"),

    /**
     * 护照
     */
    PASSPORT("2", "护照"),//("Passport"),

    /**
     * 港澳通行证
     */
    HONGKONG_AND_MACAO_PASS("3", "港澳通行证"),//("HongKong_and_Macao_pass"),

    /**
     * 台湾同胞证
     */
    TAIWAN_COMPATRIOT_CARD("4", "台湾同胞证");//("Taiwan_compatriot_card");

    private String type;
    private String typeName;

    DocumentType(String type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    public String getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    /**
     * 获取证件类型列表
     *
     * @return
     */
    public static List<DocumentType> getTypes() {
        List<DocumentType> types = new ArrayList<>();

        for (DocumentType type : DocumentType.values()) {
            types.add(type);
        }
        return types;
    }
}
