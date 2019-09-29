package com.berlski.tool.custom.style;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.berlski.tool.custom.R;

/**
 * Copyright (SaaS 技术团队)
 * FileName: MenuButtonDefaultStyle
 * Author: Berlski
 * Date: 2019/9/25 18:55
 * Description: ${ 默认下拉菜单按钮样式 }
 */
public class InfoItemDefaultStyle extends BaseInfoItemStyle{

    public InfoItemDefaultStyle(Context context) {
        super(context);
    }

    @Override
    protected Context getContext() {
        return super.getContext();
    }

    @Override
    protected Drawable getDrawable(int id) {
        return super.getDrawable(id);
    }

    @Override
    public int getColor(int id) {
        return super.getColor(id);
    }

    @Override
    public int getItemNameColor() {
        return 0xFF000000;
    }

    @Override
    public int getItemNameSize() {
        return getCount(R.dimen.sp14);
    }

    @Override
    public int getContentColor() {
        return 0xFF000000;
    }

    @Override
    public int getContentHintColor() {
        return 0xFFAAB1BB;
    }

    @Override
    public boolean getTipsIsShow() {
        return false;
    }

    @Override
    public boolean getTipsIsLeft() {
        return false;
    }

    @Override
    public Drawable getTipsIcon() {
        return super.getTipsIcon();
    }

    @Override
    public int getTipsIconSize() {
        return getCount(R.dimen.dp8);
    }

    @Override
    public int getTipsIconColor() {
        return 0xFFff0000;
    }

    @Override
    public boolean getRightIconIsShow() {
        return true;
    }

    @Override
    public int getRightIconSize() {
        return getCount(R.dimen.dp15);
    }

    @Override
    public int getRightIconColor() {
        return 0xFF000000;
    }

    @Override
    public int getContentSize() {
        return getCount(R.dimen.sp14);
    }

    @Override
    public Drawable getRightIcon() {
        return super.getRightIcon();
    }

    @Override
    public boolean getBottomLineIsShow() {
        return false;
    }

    @Override
    public int getBottomLineColor() {
        return 0xFFD3D3D3;
    }

    @Override
    public int getBottomLineHeight() {
        return getCount(R.dimen.dp0_5);
    }
}
