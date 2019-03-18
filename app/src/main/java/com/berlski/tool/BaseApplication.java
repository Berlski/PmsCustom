package com.berlski.tool;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.berlski.tool.custom.manager.MyActivityManager;
import com.berlski.tool.custom.util.AppUtil;
import com.berlski.tool.custom.manager.HttpManager;
import com.berlski.tool.custom.util.LogUtil;
import com.berlski.tool.custom.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/19.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppUtil.init(this,new AppUtil.AppInitInterface() {

            @Override
            public boolean isDeBug() {
                return true;
            }

            @Override
            public String getIpPath() {
                return "http://test.pms.mianhuagongyu.com";
            }

            @Override
            public HttpManager.HttpResponseInterface getHttpResponseInterface() {
                return new HttpManager.HttpResponseInterface() {

                    @Override
                    public Map<String, Object> addParams() {
                        Map<String, Object> map = new HashMap<>();

                        Map<String, Object> params = new HashMap<>();
                        params.put("gcid", "0100099");

                        map.put("token", "901b9bc1-165d-4de0-8c49-7da98b893c90");

                        map.put("userid", "b0585adccc16473082b63915901842af");

                        map.put("params", params);

                        return map;
                    }

                    @Override
                    public String addHeaderName() {
                        return "gcid";
                    }

                    @Override
                    public String addHeaderValue() {
                        return "0100099";
                    }

                    @Override
                    public boolean getResultCode(String s, boolean isShow) {
                        return BaseApplication.getResultCode(null,s,isShow);
                    }
                };
            }
        });
    }




    public static boolean getResultCode(Context context, String s, boolean isShow) {
        if (isShow) {
            return getResultCode(MyActivityManager.getInstance().getCurrentActivity(), s);
        } else {
            return getResultCode(s);
        }
    }

    public static boolean getResultCode(String s) {
        Context context = AppUtil.getContext();

        String code = "";
        try {
            JSONObject json = JSON.parseObject(s);
            JSONObject status = JSON.parseObject(json.getString("status"));
            code = status.getString("code");

            if (code.equals("200")) {

            } else if (code.equals("1000")) {
                //context.startActivity(new Intent(context, LoginActivity.class));
                ToastUtil.showToast( R.string.Logon_failure);
            }
        } catch (Exception e) {
            try {
                JSONObject json = JSON.parseObject(s);
                JSONObject status = JSON.parseObject(json.getString("status"));
                code = status.getString("code");
            } catch (Exception e1) {

            }
        }

        if ("200".equals(code)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean getResultCode(Context context, String s) {
        String code = "";
        try {
            JSONObject json = JSON.parseObject(s);
            JSONObject status = JSON.parseObject(json.getString("status"));
            code = status.getString("code");
            String msg = status.getString("msg");

            if (code.equals("200")) {

            } else if (code.equals("1000")) {
                //context.startActivity(new Intent(context, LoginActivity.class));
                ToastUtil.showToast( R.string.Logon_failure);
            } else {
                ToastUtil.showToast(msg);
            }
        } catch (Exception e) {
            LogUtil.e(e);

            try {
                JSONObject json = JSON.parseObject(s);
                JSONObject status = JSON.parseObject(json.getString("status"));
                String msg = status.getString("msg");
                ToastUtil.showToast(msg);
                code = status.getString("code");
            } catch (Exception e1) {
                LogUtil.e(e1);

                try {
                    JSONObject json = JSON.parseObject(s);
                    String msg = json.getString("msg");
                    ToastUtil.showToast(msg);
                } catch (Exception e2) {
                    LogUtil.e(e2);

                    ToastUtil.showToast(R.string.MSG_NET_ERROR);
                }
            }
        }

        if ("200".equals(code)) {
            return true;
        } else {
            return false;
        }
    }
}
