package com.berlski.custom.util;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.StringRes;

/**
 * 提供全局数据的工具类（Util）
 * Created by Berlski on 2018/10/17.
 */
public class AppUtil {

    private static boolean isDeBug = true;

    private static Application mApplication;

    /**
     * 初始化Application
     *
     * @param application
     */
    public static void init(Application application) {
        mApplication = application;
    }

    /**
     * 提供Application全局的  Context
     */
    public static Context getContext() {
        return getApplication().getApplicationContext();
    }

    /**
     * 提供Application全局的  Resources
     */
    public static Resources getResources() {
        return getApplication().getResources();
    }

    /**
     * 提供Application全局的  Drawable
     */
    public static Drawable getDrawable(int id) {
        return getResources().getDrawable(id);
    }

    /**
     * 获取Application
     *
     * @return
     */
    public static Application getApplication() {
        return mApplication;
    }

    /**
     * 获取资源字符串
     *
     * @param resId
     * @return
     */
    public static String getStringValue(@StringRes int resId) {
        return getApplication().getString(resId);
    }

    /**
     * 是否deBug模式
     *
     * @return
     */
    public static boolean isDeBug() {
        return isDeBug;
    }

    public static void setIsDeBug(boolean isDeBug) {
        AppUtil.isDeBug = isDeBug;
    }
}
