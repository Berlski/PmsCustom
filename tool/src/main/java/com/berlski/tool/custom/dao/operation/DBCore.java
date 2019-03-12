package com.berlski.tool.custom.dao.operation;

import android.content.Context;


import com.berlski.tool.custom.dao.DaoMaster;
import com.berlski.tool.custom.dao.DaoSession;
import com.berlski.tool.custom.util.AppUtil;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 描述 ： 数据库的工具类
 * Created by Berlski on 2019/1/22.
 */
public class DBCore {

    private static DBCore instance;

    private static final String DEFAULT_DB_NAME = "PMS_app_Green_dao.db";  //数据库名字
    private static final String DEFALLT_DA_PASSWORD = "PMS_app_Green_dao";  //加密数据库的密码
    private Context mContext;
    private String mDbName;//得到数据库的名字的字段
    private DaoMaster mDaoMaster; //数据库的管理者
    private DaoSession mDaoSession; //此对象是对数据库进行增删改查的
    private DaoMaster.DevOpenHelper helper;

    public static DBCore instance() {
        if (instance == null) {
            instance = new DBCore();
            return instance;
        } else {
            return instance;
        }
    }

    public void init(Context context) {
        init(context, DEFAULT_DB_NAME);
    }

    private void init(Context context, String dbName) {
        if (context == null) {
            throw new IllegalArgumentException("出错了");
        }
        mContext = AppUtil.getContext();
        mDbName = dbName;
    }

    /**
     * 得到数据库的管理类
     *
     * @return
     */
    private DaoMaster getDaoMaster(boolean isEncryption) {
        if (mDaoMaster == null) {

            if (isEncryption) {
                //得到数据库加密的管理类
                mDaoMaster = new DaoMaster(getHelper().getEncryptedReadableDb(DEFALLT_DA_PASSWORD));

            } else {
                //得到数据库正常的管理类
                mDaoMaster = new DaoMaster(getHelper().getWritableDatabase());
            }
        }
        return mDaoMaster;
    }

    public DaoMaster getDaoMaster() {
        if (mDaoMaster == null) {
            return getDaoMaster(false);
        }

        return mDaoMaster;
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public DaoSession getNewSession() {
        return getDaoMaster().newSession();
    }

    /**
     * 得到数据库加密 / 不加密的管理类
     *
     * @return
     */
    public DaoSession getDaoSession(boolean isEncryption) {
        if (mDaoSession == null) {
            if (mDaoMaster == null) {
                mDaoMaster = getDaoMaster(isEncryption);
            }
            mDaoSession = getNewSession();
        }
        return mDaoSession;
    }

    private DaoMaster.DevOpenHelper getHelper(){
        if (helper == null) {
            helper = new DaoMaster.DevOpenHelper(mContext, mDbName);
        }
        return helper;
    }

    /**
     * 在 QueryBuilder 类中内置两个 Flag 用于方便输出执行的 SQL 语句与传递参数的值
     */
    public void enableQueryBuilderLog() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    /*public class MyOpenHelper extends DaoMaster.DevOpenHelper {
        public MyOpenHelper(Context context, String name) {
            super(context, name);
        }
    }*/
}
