package com.berlski.tool.custom.manager;

import android.content.Context;

import com.berlski.tool.custom.dao.DaoSession;
import com.berlski.tool.custom.dao.operation.DBCore;

/**
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 描述 ： 数据库管理类
 * Created by Berlski on 2019/1/22.
 */
public class GreenDaoManager {

    private static GreenDaoManager instance;

    public static GreenDaoManager getInstance() {
        if (instance == null) {
            instance = new GreenDaoManager();
            return instance;
        } else {
            return instance;
        }
    }


    public void init(Context context) {
        DBCore.instance().init(context);
        initDatabase();
    }

    private void initDatabase() {
        DBCore.instance().enableQueryBuilderLog();
    }

    /**
     * 得到正常的加密的、不加密的
     *
     * @param blean 是否加密
     * @return
     */
    public DaoSession getDaoSession(boolean blean) {
        return DBCore.instance().getDaoSession(blean);
    }

    /**
     * 得到正常的不加密的
     */
    public DaoSession getDaoSession() {
        return DBCore.instance().getDaoSession(false);
    }
}
