package com.berlski.custom;

import android.app.Application;

import com.berlski.custom.util.AppUtil;

/**
 * Created by Administrator on 2015/11/19.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppUtil.init(this);
    }
}
