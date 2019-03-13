package com.berlski.tool.custom.enums;

/**
 * Created by Administrator on 2015/12/17.
 */
public enum NetUrlEnum {

    /**
     * 例：
     * 获取用户列表
     */
    NET_URL_ENUM("/net_url_enum/get_user_list", "获取用户列表");

    public String getUrl() {
        return url;
    }

    public String getUrlName() {
        return urlName;
    }

    private String url;
    private String urlName;

    NetUrlEnum(String url, String urlName) {
        this.url = url;
        this.urlName = urlName;
    }
}
