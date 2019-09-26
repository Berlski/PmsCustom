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
public class MenuButtonDefaultStyle extends BaseMenuButtonStyle{

    public MenuButtonDefaultStyle(Context context) {
        super(context);
    }

    @Override
    public int getTextOpenColor() {
        return super.getTextOpenColor();
    }

    @Override
    public int getTextCloseColor() {
        return 0xFFff0000;
    }

    @Override
    public int getIconOpenColor() {
        return super.getIconOpenColor();
    }

    @Override
    public int getIconCloseColor() {
        return super.getIconCloseColor();
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
    public Drawable getOpenIcon() {
        return super.getOpenIcon();
    }

    @Override
    public Drawable getCloseIcon() {
        return super.getCloseIcon();
    }

    @Override
    public int getIconSize() {
        return getCount(R.dimen.dp15);
    }

    @Override
    public int getTextSize() {
        return getCount(R.dimen.sp14);
    }

    @Override
    public boolean isShowBottomLine() {
        return false;
    }

    @Override
    public int getBottomLineColor() {
        return 0xFFff0000;
    }

    @Override
    public int getBottomLineHeight() {
        return getCount(R.dimen.dp1);
    }
}
