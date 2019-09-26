package com.berlski.tool.custom.style;

import android.graphics.drawable.Drawable;

/**
 * Copyright (SaaS 技术团队)
 * FileName: MenuButtonStyle
 * Author: Berlski
 * Date: 2019/9/25 17:45
 * Description: ${ 下拉菜单按钮样式接口 }
 */
public interface MenuButtonStyle {

    int getTextOpenColor();     //返回开启时文字颜色
    int getTextCloseColor();    //返回关闭时文字颜色

    int getIconOpenColor();     //返回开启时图标颜色
    int getIconCloseColor();    //返回关闭时图标颜色

    Drawable getOpenIcon();     //返回开启图标
    Drawable getCloseIcon();    //返回关闭图标

    int getIconSize();          //返回图标大小
    int getTextSize();          //返回文字大小

    boolean isShowBottomLine(); //返回是否显示底部横线
    int getBottomLineColor();   //返回底部横线颜色
    int getBottomLineHeight();  //返回底部横线高度
}
