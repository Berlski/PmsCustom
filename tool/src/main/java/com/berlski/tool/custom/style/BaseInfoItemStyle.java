package com.berlski.tool.custom.style;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DimenRes;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.util.UiUtil;

/**
 * Copyright (SaaS 技术团队)
 * FileName: BaseMenuButtonStyle
 * Author: Berlski
 * Date: 2019/9/25 18:10
 * Description: ${ 下拉菜单按钮样式基类 }
 */
public abstract class BaseInfoItemStyle implements InfoItemStyle {

    private Context context;

    public BaseInfoItemStyle(Context context) {
        this.context = context;
    }

    protected Context getContext() {
        return context;
    }

    protected Drawable getDrawable(int id) {
        return UiUtil.getDrawable(getContext(), id);
    }

    public int getColor(int id) {
        return UiUtil.getColor(getContext(), id);
    }

    /**
     * 根据dimen值计算返回对应屏幕的px值，
     *
     * @param id R.dimen.id
     * @return
     */
    protected int getCount(@DimenRes int id) {
        return UiUtil.getCount(getContext(), id);
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
    public int getContentSize() {
        return getCount(R.dimen.sp14);
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
        return getDrawable(R.drawable.ic_stars);
    }

    @Override
    public int getTipsIconSize() {
        return getCount(R.dimen.dp10);
    }

    @Override
    public int getTipsIconColor() {
        return 0xFFff0000;
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
    public Drawable getRightIcon() {
        return getDrawable(R.drawable.ic_keyboard_arrow_right);
    }

    @Override
    public boolean getBottomLineIsShow() {
        return false;
    }

    @Override
    public int getBottomLineColor() {
        return 0xFF000000;
    }

    @Override
    public int getBottomLineHeight() {
        return getCount(R.dimen.dp1);
    }
}