package com.berlski.tool.custom.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 对用户信息的常用属性统一管理
 */
public class UserUtil<T> {

    private static UserUtil mUserUtil;

    public static final String USER_DATA = "login_user_data";

    private T mUser;

    public static UserUtil builder(UserDateInter inter) {
        if (mUserUtil == null) {
            synchronized (UserUtil.class) {
                if (mUserUtil == null) {
                    mUserUtil = new UserUtil(inter);
                }
            }
        }

        return mUserUtil;
    }


    private UserUtil(UserDateInter inter) {
        this.inter = inter;
    }

    /**
     * 获取当前用户信息，如果静态User对象为空，就去获取序列化的User对象，如果都为空就只能为空了
     *
     * @return
     */
    public T getUser() {
        if (mUser == null) {
            mUser = (T) PreferenceUtils.getData(USER_DATA, getInterClass());
        }
        return mUser;
    }

    /**
     * 序列化当前用户信息，并保存静态对象，以供重复使用
     *
     * @return
     */
    public void setUser(T user) {
        mUser = user;
        if (user != null) {
            PreferenceUtils.setData(USER_DATA, user);
        }
    }

    /**
     * 删除序列化的用户信息
     */
    public void removeUserData() {
        setUser(null);
        PreferenceUtils.remove(USER_DATA);
    }

    /**
     * 获取User对象的公司id
     *
     * @return
     */
    public String getCompanyId() {
        if (getUser() == null || StringUtil.isEmpty(inter.getCompanyId(getUser()))) {
            return "";
        }

        return inter.getCompanyId(getUser());
    }

    /**
     * 获取User对象的手机号
     *
     * @return
     */
    public String getPhoneNo() {
        if (getUser() == null || StringUtil.isEmpty(inter.getPhoneNo(getUser()))) {
            return "";
        }

        return inter.getPhoneNo(getUser());
    }

    /**
     * 获取User对象的用户名称
     *
     * @return
     */
    public String getUserName() {
        if (getUser() == null || StringUtil.isEmpty(inter.getUserName(getUser()))) {
            return "";
        }

        return inter.getUserName(getUser());
    }

    /**
     * 获取User对象的用户id
     *
     * @return
     */
    public String getUserId() {
        if (getUser() == null || StringUtil.isEmpty(inter.getUserId(getUser()))) {
            return "";
        }

        return inter.getUserId(getUser());
    }

    /**
     * 获取User对象的token
     *
     * @return
     */
    public String getUserToken() {
        if (getUser() == null || StringUtil.isEmpty(inter.getUserToken(getUser()))) {
            return "";
        }

        return inter.getUserToken(getUser());
    }

    /**
     * 获取User对象的公司所在城市id
     *
     * @return
     */
    public String getCompanyCityId() {
        if (getUser() == null || StringUtil.isEmpty(inter.getCompanyCityId(getUser()))) {
            return "";
        }
        return inter.getCompanyCityId(getUser());
    }

    /**
     * 获取User对象的公司所在城市名称
     *
     * @return
     */
    public String getCompanyCityName() {
        if (getUser() == null || StringUtil.isEmpty(inter.getCompanyCityName(getUser()))) {
            return "";
        }
        return inter.getCompanyCityName(getUser());
    }

    /**
     * 获取User对象的公司名称
     *
     * @return
     */
    public String getCompanyName() {
        if (getUser() == null || StringUtil.isEmpty(inter.getCompanyName(getUser()))) {
            return "";
        }
        return inter.getCompanyName(getUser());
    }

    /**
     * 获取User对象的头像地址
     *
     * @return
     */
    public String getUserHead() {
        if (getUser() == null || StringUtil.isEmpty(inter.getUserHead(getUser()))) {
            return "";
        }
        return inter.getUserHead(getUser());
    }

    /**
     * 获取User对象的纬度
     *
     * @return
     */
    public String getLatString() {
        if (getUser() == null || StringUtil.isEmpty(inter.getLat(getUser()))) {
            return "";
        }
        return inter.getLat(getUser());
    }

    /**
     * 获取User对象的纬度
     *
     * @return
     */
    public double getLat() {
        if (getUser() == null || StringUtil.isEmpty(inter.getLat(getUser()))) {
            return 0;
        }
        return Double.parseDouble(inter.getLat(getUser()));
    }

    /**
     * 获取User对象的经度
     *
     * @return
     */
    public String getLonString() {
        if (getUser() == null || StringUtil.isEmpty(inter.getLon(getUser()))) {
            return "";
        }
        return inter.getLon(getUser());
    }

    /**
     * 获取User对象的经度
     *
     * @return
     */
    public double getLon() {
        if (getUser() == null || StringUtil.isEmpty(inter.getLon(getUser()))) {
            return 0;
        }
        return Double.parseDouble(inter.getLon(getUser()));
    }

    /**
     * 获取User对象的部门id
     *
     * @return
     */
    public String getDptmId() {
        if (getUser() == null || StringUtil.isEmpty(inter.getDptmId(getUser()))) {
            return "";
        }
        return inter.getDptmId(getUser());
    }

    /**
     * 获取User对象的部门名称
     *
     * @return
     */
    public String getDptmName() {
        if (getUser() == null || StringUtil.isEmpty(inter.getDptmName(getUser()))) {
            return "";
        }
        return inter.getDptmName(getUser());
    }

    /**
     * 获取User对象的部门名称
     *
     * @return
     */
    public String getLastDepartment() {
        if (getUser() == null || StringUtil.isEmpty(inter.getLastDepartment(getUser()))) {
            return "";
        }
        return inter.getLastDepartment(getUser());
    }

    /**
     * 获取User对象的性别，1 为男
     *
     * @return
     */
    public String getGender() {
        if (getUser() == null || StringUtil.isEmpty(inter.getGender(getUser()))) {
            return "";
        }
        return inter.getGender(getUser());
    }

    /**
     * 获取接口和类上的泛型类型
     *
     * @return
     */
    private Class<T> getInterClass() {
        Type[] types = inter.getClass().getGenericInterfaces();
        ParameterizedType parameterized = (ParameterizedType) types[0];
        final Class<T> tClass = (Class<T>) parameterized.getActualTypeArguments()[0];
        return tClass;
    }

    private UserDateInter inter;

    public interface UserDateInter<T> {

        String getCompanyId(T user);

        String getPhoneNo(T user);

        String getUserName(T user);

        String getUserId(T user);

        String getUserToken(T user);

        String getCompanyCityId(T user);

        String getCompanyCityName(T user);

        String getCompanyName(T user);

        String getUserHead(T user);

        String getLat(T user);

        String getLon(T user);

        String getLastDepartment(T user);

        String getGender(T user);

        String getDptmId(T user);

        String getDptmName(T user);
    }
}
