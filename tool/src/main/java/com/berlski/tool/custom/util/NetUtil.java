package com.berlski.tool.custom.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import com.berlski.tool.custom.R;

import static android.content.Context.WIFI_SERVICE;

/**
 * copywrite 2015-2020 金地物业
 * 不能修改和删除上面的版权声明
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 网络工具类（Util）
 * Created by Berlski on 2018/10/19.
 */
public class NetUtil {

    /**
     * 判断网络是否连接
     *
     * @return 返回Boolean值，true为连接中
     */
    public static boolean isNetworkAvailable() {
        Context context = AppUtil.getContext();

        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm == null) {

            } else {
                //如果仅仅是用来判断网络连接
                //则可以使用 cm.getActiveNetworkInfo().isAvailable();
                NetworkInfo[] info = cm.getAllNetworkInfo();
                if (info != null) {
                    for (int i = 0; i < info.length; i++) {
                        if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
            ToastUtil.showToast(R.string.MSG_NET_ERROR);
            return false;
        }
        return false;
    }

    /**
     * 获取WIFI管理，WIFI名称
     *
     * @return
     */
    public static WifiInfo getWifiInfo() {
        Context context = AppUtil.getContext();

        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        return wifiInfo;
    }
}
