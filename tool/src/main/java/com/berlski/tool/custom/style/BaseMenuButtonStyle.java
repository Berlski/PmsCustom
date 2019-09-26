package com.berlski.tool.custom.style;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
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
public abstract class BaseMenuButtonStyle implements MenuButtonStyle {

    private Context mContext;

    public BaseMenuButtonStyle(Context context) {
        mContext = context;
    }

    protected Context getContext() {
        return mContext;
    }

    protected Drawable getDrawable(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return getContext().getResources().getDrawable(id, null);
        } else {
            return getContext().getResources().getDrawable(id);
        }
    }

    public int getColor(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getContext().getResources().getColor(id, null);
        } else {
            return getContext().getResources().getColor(id);
        }
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
    public int getIconCloseColor() {
        return 0xFF000000;
    }

    @Override
    public int getIconOpenColor() {
        return 0xFF31c27c;
    }

    @Override
    public int getTextCloseColor() {
        return 0xFF000000;
    }

    @Override
    public int getTextOpenColor() {
        return 0xFF31c27c;
    }

    @Override
    public Drawable getOpenIcon() {
        return getDrawable(R.drawable.ic_menu_button_icon_up);
    }

    @Override
    public Drawable getCloseIcon() {
        return getDrawable(R.drawable.ic_menu_button_icon_down);
    }

    @Override
    public int getIconSize() {
        return getCount(R.dimen.dp15);
    }

    @Override
    public int getTextSize() {
        return getCount(R.dimen.sp15);
    }

    @Override
    public boolean isShowBottomLine() {
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
