package com.berlski.tool.custom.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.StringRes;

import com.berlski.tool.custom.manager.DialogManager;
import com.berlski.tool.custom.manager.HttpManager;
import com.berlski.tool.custom.manager.MyActivityManager;

/**
 * 提供全局数据的工具类（Util）
 * Created by Berlski on 2018/10/17.
 */
public class AppUtil {

    private static AppInitInterface appInitInterface;

    /**
     * 初始化Application
     */
    public static void init(AppInitInterface appInitInterface) {

        AppUtil.appInitInterface = appInitInterface;

        appInitInterface.getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {
                MyActivityManager.getInstance().setCurrentActivity(activity);
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
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
        return AppUtil.appInitInterface.getApplication();
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

    public static String getIpPath() {
        return AppUtil.appInitInterface.getIpPath();
    }

    /**
     * 是否deBug模式
     *
     * @return
     */
    public static boolean isDeBug() {
        return AppUtil.appInitInterface.isDeBug();
    }

    public static HttpManager.HttpResponseInterface getHttpResponseInterface() {
        return AppUtil.appInitInterface.getHttpResponseInterface();
    }

    /**
     * 接口限定初始化需要的数据，
     */
    public interface AppInitInterface {

        Application getApplication();

        /**
         * 接口回调时，表示请求需要拼接公用参数
         */
        boolean isDeBug();

        String getIpPath();

        HttpManager.HttpResponseInterface getHttpResponseInterface();
    }
}
