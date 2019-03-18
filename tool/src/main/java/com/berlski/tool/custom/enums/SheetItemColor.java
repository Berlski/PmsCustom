package com.berlski.tool.custom.enums;

import android.support.annotation.ColorRes;

import com.berlski.tool.custom.R;

public enum SheetItemColor {
    Blue(R.color.blue_text),

    Red(R.color.red_text),

    Black(R.color.black),

    Green(R.color.green_text),

    ColorStyle(R.color.color_styles);

    private @ColorRes
    int colorRes;

    SheetItemColor(@ColorRes int colorRes) {
        this.colorRes = colorRes;
    }

    public @ColorRes
    int getRes() {
        return colorRes;
    }

    public void setRes(@ColorRes int name) {
        this.colorRes = name;
    }
}
