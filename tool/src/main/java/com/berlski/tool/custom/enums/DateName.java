package com.berlski.tool.custom.enums;

public enum DateName {

    /**
     * 今天
     */
    TODAY("今天"),

    /**
     * 昨天
     */
    YESTERDAY("昨天"),

    /**
     * 明天
     */
    TOMORROW("明天"),

    /**
     * 前天
     */
    BEFORE_YESTERDAY("前天"),

    /**
     * 后天
     */
    AFTER_TOMORROW("后天"),

    /**
     * 星期日
     */
    SUNDAY("星期日"),

    /**
     * 星期一
     */
    MONDAY("星期一"),

    /**
     * 星期二
     */
    TUESDAY("星期二"),

    /**
     * 星期三
     */
    WEDNESDAY("星期三"),

    /**
     * 星期四
     */
    THURSDAY("星期四"),

    /**
     * 星期五
     */
    FRIDAY("星期五"),

    /**
     * 星期六
     */
    SATURDAY("星期六");

    private String dateName;

    DateName(String dateName) {
        this.dateName = dateName;
    }

    public String getDateName() {
        return this.dateName;
    }
}
