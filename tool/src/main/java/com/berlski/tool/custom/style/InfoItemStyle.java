package com.berlski.tool.custom.style;

import android.graphics.drawable.Drawable;

/**
 * Copyright (SaaS 技术团队)
 * FileName: InfoItemStyle
 * Author: Berlski
 * Date: 2019/9/27 11:31
 * Description: ${ 信息编辑条目样式 }
 */
public interface InfoItemStyle {

    int getItemNameColor();         //返回条目名称文字颜色
    int getItemNameSize();          //返回条目名称文字大小

    int getContentColor();          //返回内容文字大小
    int getContentHintColor();      //返回内容提示文字大小
    int getContentSize();           //返回内容文字大小

    boolean getTipsIsShow();        //返回提示图标是否显示
    boolean getTipsIsLeft();        //返回提示图标是否居左
    Drawable getTipsIcon();         //返回提示图标
    int getTipsIconSize();          //返回提示图标大小
    int getTipsIconColor();         //返回提示图标颜色

    boolean getRightIconIsShow();   //返回右侧图标是否显示
    Drawable getRightIcon();        //返回右侧图标
    int getRightIconSize();         //返回右侧图标大小
    int getRightIconColor();        //返回右侧图标颜色

    boolean getBottomLineIsShow();  //返回是否显示底部横线
    int getBottomLineColor();       //返回底部横线颜色
    int getBottomLineHeight();      //返回底部横线高度
}
