package com.berlski.tool.custom.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 对sp数据存储管理的工具（Util）
 * Created by ws on 2016/2/22.
 */
public class PreferenceUtils {

    private static SharedPreferences mSp;
    private static final String SP_NAME = "config";
    private static final String ADMIN = "admin";
    private static final String USER_NAME = "login_userName";
    private static final String USER_GCID = "login_gcid";
    private static final String USER_PASSWORD = "login_password";
    public static final String USER_DATA = "login_user_data";

    /**
     * 获得sharePreference内存对象
     *
     * @return
     */
    private static SharedPreferences getSp() {
        if (mSp == null) {
            mSp = getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        return mSp;
    }

    public static Context getContext() {
        return AppUtil.getContext();
    }

    /**
     * 获取公司id
     *
     * @return
     */
    public static String getGcid() {
        return getString(USER_GCID);
    }

    /**
     * 序列化公司id
     *
     * @param gcid
     */
    public static void setGcid(String gcid) {
        setString(USER_GCID, gcid);
    }

    /**
     * 判断登陆名、账号是否为admin账号
     *
     * @return
     */
    public static boolean isAdmin() {
        return ADMIN.equals(getString(USER_NAME));
    }

    /**
     * 获取登陆名、账号
     *
     * @return
     */
    public static String getUserName() {
        return getString(USER_NAME);
    }

    /**
     * 序列化登录名、账号
     *
     * @param userName
     */
    public static void setUserName(String userName) {
        setString(USER_NAME, userName);
    }

    /**
     * 获取用户密码
     *
     * @return
     */
    public static String getPassword() {
        return getString(USER_PASSWORD);
    }

    /**
     * 序列化用户密码
     *
     * @param password
     */
    public static void setPassword(String password) {
        setString(USER_PASSWORD, password);
    }



    /**
     * 获取boolean类型的值
     *
     * @param key      对应的键
     * @param defValue 默认值
     * @return
     */
    public static boolean getBoolean(String key, boolean defValue) {
        SharedPreferences sp = getSp();
        return sp.getBoolean(key, defValue);
    }

    /**
     * 获取boolean类型的值,如果没有对应的值，默认值返回false
     *
     * @param key 对应的键
     * @return
     */
    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    /**
     * 设置int类型的值
     *
     * @param key
     * @param value
     */
    public static void setInt(String key, int value) {
        SharedPreferences sp = getSp();
        Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 设置boolean类型的值
     *
     * @param key
     * @param value
     */
    public static void setBoolean(String key, boolean value) {
        SharedPreferences sp = getSp();
        Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 获取int类型的值
     *
     * @param key      对应的键
     * @param defValue 如果没有对应的值，
     * @return
     */
    public static int getInt(String key, int defValue) {
        SharedPreferences sp = getSp();
        return sp.getInt(key, defValue);
    }

    /**
     * 获取String类型的值,如果没有对应的值，默认值返回""
     *
     * @param key 对应的键
     * @return
     */
    public static String getString(String key) {
        return getString(key, "");
    }

    /**
     * 获取String类型的值,如果没有对应的值，则返回传入的默认值
     *
     * @param key      对应的键
     * @param defValue 默认值
     * @return
     */
    public static String getString(String key, String defValue) {
        SharedPreferences sp = getSp();
        return sp.getString(key, defValue);
    }

    /**
     * 设置String类型的值
     *
     * @param key
     * @param value
     */
    public static void setString(String key, String value) {
        SharedPreferences sp = getSp();
        Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 获取long类型的值
     *
     * @param key      对应的键
     * @param defValue 如果没有对应的值，
     * @return
     */
    public static long getLong(String key, long defValue) {
        SharedPreferences sp = getSp();
        return sp.getLong(key, defValue);
    }

    /**
     * 获取long类型的值,如果没有对应的值，默认值返回0
     *
     * @param key 对应的键
     * @return
     */
    public static Long getLong(String key) {
        return getLong(key, 0);
    }

    /**
     * 设置Long类型的值
     *
     * @param key
     * @param value
     */
    public static void setLong(String key, long value) {
        SharedPreferences sp = getSp();
        Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * 根据key值删除指定的数据
     *
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences sp = getSp();
        Editor editor = sp.edit();
        editor.remove(key);
        editor.commit();
    }


    /**
     * 用于保存集合
     *
     * @param key key
     * @param map map数据
     * @return 保存结果
     */
    public static <K, V> boolean setHashMap(String key, Map<K, V> map) {
        boolean result;
        Editor editor = getSp().edit();

        try {
            Gson gson = new Gson();
            String json = gson.toJson(map);
            editor.putString(key, json);
            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        editor.apply();
        return result;
    }

    /**
     * 用于获取集合
     *
     * @param key key
     * @return HashMap
     */
    public static <V> HashMap<String, V> getHashMap(String key, Class<V> clsV) {

        String json = getSp().getString(key, "");
        LogUtil.e("getHashMap -- ", json);

        if (StringUtil.isNotEmpty(json)) {

            HashMap<String, V> map = new HashMap<>();
            Gson gson = new Gson();
            JsonObject obj = new JsonParser().parse(json).getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> entrySet = obj.entrySet();

            for (Map.Entry<String, JsonElement> entry : entrySet) {
                String entryKey = entry.getKey();
                JsonObject value = (JsonObject) entry.getValue();
                map.put(entryKey, gson.fromJson(value, clsV));
            }
            return map;
        }

        return null;
    }


    /**
     * 保存数据
     *
     * @param key
     * @param data
     */
    public void saveData(String key, Object data) {
        String type = data.getClass().getSimpleName();

        Editor editor = getSp().edit();

        if ("Integer".equals(type)) {
            editor.putInt(key, (Integer) data);
        } else if ("Boolean".equals(type)) {
            editor.putBoolean(key, (Boolean) data);
        } else if ("String".equals(type)) {
            editor.putString(key, (String) data);
        } else if ("Float".equals(type)) {
            editor.putFloat(key, (Float) data);
        } else if ("Long".equals(type)) {
            editor.putLong(key, (Long) data);
        }

        editor.commit();
    }

    /**
     * 得到数据
     *
     * @param key
     * @param defValue
     * @return
     */
    public Object getData(String key, Object defValue) {

        String type = defValue.getClass().getSimpleName();
        if ("Integer".equals(type)) {
            return getSp().getInt(key, (Integer) defValue);
        } else if ("Boolean".equals(type)) {
            return getSp().getBoolean(key, (Boolean) defValue);
        } else if ("String".equals(type)) {
            return getSp().getString(key, (String) defValue);
        } else if ("Float".equals(type)) {
            return getSp().getFloat(key, (Float) defValue);
        } else if ("Long".equals(type)) {
            return getSp().getLong(key, (Long) defValue);
        }

        return null;
    }

    /**
     * 用于获取数据
     *
     * @param key  key
     * @param clsV class
     * @param <V>
     * @return
     */
    public static <V> Object getData(String key, Class<V> clsV) {
        String value = getString(key);

        return JSON.parseObject(value, clsV);
    }

    /**
     * 用于保存数据
     *
     * @param key    key
     * @param object
     */
    public static void setData(String key, Object object) {
        setString(key, JSON.toJSONString(object));
    }
}
