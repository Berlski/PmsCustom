package com.berlski.tool;

import android.app.Application;

import com.berlski.tool.custom.util.AppUtil;
import com.berlski.tool.custom.manager.HttpManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/19.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppUtil.init(new AppUtil.AppInitInterface() {
            @Override
            public Application setApplication() {
                return BaseApplication.this;
            }

            @Override
            public boolean isDeBug() {
                return true;
            }

            @Override
            public String setIpPath() {
                return "http://192.168.0.000:0000";
            }

            @Override
            public HttpManager.HttpResponseInterface setHttpResponseInterface() {
                return new HttpManager.HttpResponseInterface() {

                    @Override
                    public Map<String, Object> addParams() {
                        Map<String, Object> map = new HashMap<>();

                        Map<String, Object> paramsJSON = new HashMap<>();

                        map.put("token", "token");

                        paramsJSON.put("gcid", "gcid");

                        map.put("userid", "userid");

                        map.put("params", paramsJSON);

                        return map;
                    }

                    @Override
                    public String addHeaderName() {
                        return "gcid";
                    }

                    @Override
                    public String addHeaderValue() {
                        return "gcid";
                    }

                    @Override
                    public boolean getResultCode(String s, boolean isShow) {
                        return false;
                    }
                };
            }
        });
    }
}
