package com.berlski.tool.custom.manager;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.fastjson.JSONObject;
import com.berlski.tool.custom.R;
import com.berlski.tool.custom.dialog.LoadingDialog;
import com.berlski.tool.custom.enums.NetUrlEnum;
import com.berlski.tool.custom.util.AppUtil;
import com.berlski.tool.custom.util.LogUtil;
import com.berlski.tool.custom.util.ToastUtil;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

import static com.lzy.okgo.utils.HttpUtils.runOnUiThread;

/**
 * okHttp二次封装
 * <p>
 * Created by Berlski on 2018/12/20.
 */

public class HttpManager {

    private static HttpManager mHttpManager = null;

    public static HttpManager getInstance() {
        if (mHttpManager == null) {
            synchronized (HttpManager.class) {
                if (mHttpManager == null) {
                    mHttpManager = new HttpManager();
                }
            }
        }

        HttpManager.responseInterface = AppUtil.getHttpResponseInterface();

        return mHttpManager;
    }

    /**
     * post请求，默认时间60秒，不展示加载动画
     *
     * @param url    网络接口枚举
     * @param params 请求参数
     * @param inter  回调接口
     * @return this
     */
    public HttpManager post(NetUrlEnum url, JSONObject params, AbHttpResponseListenerInterface inter) {
        toPostRequest(null, 0, url, params, false, inter);
        return this;
    }

    /**
     * post请求，默认时间60秒
     *
     * @param activity 需要获取activity的上下文
     * @param url      网络接口枚举
     * @param params   请求参数
     * @param isShow   判断是否展示吐司 或 加载动画
     * @param inter    回调接口
     * @return this
     */
    public HttpManager post(Activity activity, NetUrlEnum url, JSONObject params, boolean isShow, AbHttpResponseListenerInterface inter) {
        toPostRequest(activity, 0, url, params, isShow, inter);
        return this;
    }

    /**
     * post请求，默认时间60秒
     *
     * @param fragment 需要获取fragment的上下文
     * @param url      网络接口枚举
     * @param params   请求参数
     * @param isShow   判断是否展示吐司 或 加载动画
     * @param inter    回调接口
     * @return this
     */
    public HttpManager post(Fragment fragment, NetUrlEnum url, JSONObject params, boolean isShow, AbHttpResponseListenerInterface inter) {
        toPostRequest(fragment.getContext(), 0, url, params, isShow, inter);
        return this;
    }

    /**
     * post请求，传入时间大于60秒为有效，不展示加载动画
     *
     * @param time   请求延时时间，必须大于60秒
     * @param url    网络接口枚举
     * @param params 请求参数
     * @param inter  回调接口
     * @return this
     */
    public HttpManager post(int time, NetUrlEnum url, JSONObject params, AbHttpResponseListenerInterface inter) {
        toPostRequest(null, time, url, params, false, inter);
        return this;
    }

    /**
     * post请求，传入时间大于60秒为有效
     *
     * @param activity 需要获取activity的上下文
     * @param time     请求延时时间，必须大于60秒
     * @param url      网络接口枚举
     * @param params   请求参数
     * @param isShow   判断是否展示吐司 或 加载动画
     * @param inter    回调接口
     * @return this
     */
    public HttpManager post(Activity activity, int time, NetUrlEnum url, JSONObject params, boolean isShow, AbHttpResponseListenerInterface inter) {
        toPostRequest(activity, time, url, params, isShow, inter);
        return this;
    }

    /**
     * post请求，传入时间大于60秒为有效
     *
     * @param fragment 需要获取fragment的上下文
     * @param time     请求延时时间，必须大于60秒
     * @param url      网络接口枚举
     * @param params   请求参数
     * @param isShow   判断是否展示吐司 或 加载动画
     * @param inter    回调接口
     * @return this
     */
    public HttpManager post(Fragment fragment, int time, NetUrlEnum url, JSONObject params, boolean isShow, AbHttpResponseListenerInterface inter) {
        toPostRequest(fragment.getContext(), time, url, params, isShow, inter);
        return this;
    }

    /**
     * get请求，默认时间60秒
     *
     * @param activity 需要获取activity的上下文
     * @param url      网络接口枚举
     * @param isShow   判断是否展示吐司 或 加载动画
     * @param inter    回调接口
     * @return this
     */
    public HttpManager get(Activity activity, String url, boolean isShow, AbHttpResponseListenerInterface inter) {
        toGetRequest(activity, 0, url, isShow, inter);
        return this;
    }

    /**
     * get请求，默认时间60秒
     *
     * @param fragment 需要获取fragment的上下文
     * @param url      网络接口枚举
     * @param isShow   判断是否展示吐司 或 加载动画
     * @param inter    回调接口
     * @return this
     */
    public HttpManager get(Fragment fragment, String url, boolean isShow, AbHttpResponseListenerInterface inter) {
        toGetRequest(fragment.getContext(), 0, url, isShow, inter);
        return this;
    }

    /**
     * get请求，传入时间大于60秒为有效
     *
     * @param activity 需要获取activity的上下文
     * @param time     请求延时时间，必须大于60秒
     * @param url      网络接口枚举
     * @param isShow   判断是否展示吐司 或 加载动画
     * @param inter    回调接口
     * @return this
     */
    public HttpManager get(Activity activity, int time, String url, boolean isShow, AbHttpResponseListenerInterface inter) {
        toGetRequest(activity, time, url, isShow, inter);
        return this;
    }

    /**
     * get请求，传入时间大于60秒为有效
     *
     * @param fragment 需要获取fragment的上下文
     * @param time     请求延时时间，必须大于60秒
     * @param url      网络接口枚举
     * @param isShow   判断是否展示吐司 或 加载动画
     * @param inter    回调接口
     * @return this
     */
    public HttpManager get(Fragment fragment, int time, String url, boolean isShow, AbHttpResponseListenerInterface inter) {
        toGetRequest(fragment.getContext(), time, url, isShow, inter);
        return this;
    }

    /**
     * @param time
     * @return
     */
    private OkHttpClient initOkHttpClient(int time) {

        //如果时间小于60，则默认为60秒
        if (time < 60) {
            time = 60;
        }

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogUtil.d("OkhHttp", message);
                    }
                }).setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(time, TimeUnit.SECONDS)
                .writeTimeout(time, TimeUnit.SECONDS)
                .readTimeout(time, TimeUnit.SECONDS);

        return builder.build();
    }

    /**
     * post请求，传入时间大于60秒为有效
     *
     * @param mContext 需要获取上下文
     * @param time     请求延时时间，必须大于60秒
     * @param url      网络接口枚举
     * @param params   请求参数
     * @param isShow   判断是否展示吐司 或 加载动画
     * @param inter    回调接口
     */
    private void toPostRequest(final Context mContext, int time, final NetUrlEnum url, JSONObject params, final boolean isShow, final AbHttpResponseListenerInterface inter) {
        //将parmasJSON添加到abRequestParams中，并添加三个公共参数：token gcid userid
        //params = putDataJSON(params);
        String data = params.toString();

        //2,创建Request

        //获取gcid，用以设定请求头
        /*String gcid = "";
        if (StringUtil.isNotEmpty(AppUtil.getCompanyId())) {
            gcid = AppUtil.getCompanyId();

        } else if (StringUtil.isNotEmpty(data)) {
            //JSONObject json = JSON.parseObject(data);
            JSONObject jsonParams = JSON.parseObject(params.getString("params"));
            gcid = jsonParams.getString("gcid").trim();
        }*/

        //2,创建一个Request，设定为post请求
        //设定请求参数、请求接口、请求头
        RequestBody formBody = new FormBody.Builder().add("data", data).build();
        Request request = new Request.Builder().url(AppUtil.getIpPath() + url.getUrl()).addHeader(responseInterface.addHeaderName(), responseInterface.addHeaderValue()).post(formBody).build();


        LogUtil.e("http_tag--", url.getUrlName() + "  + url === : \n" + AppUtil.getIpPath() + url.getUrl());
        LogUtil.e("http_tag--", url.getUrlName() + "  + data === : \n" + data);


        //判断是否展示加载动画
        if (isShow) {
            LoadingDialog.builder(mContext).show();
        }

        //3,创建一个call对象
        OkHttpClient okHttpClient = initOkHttpClient(time);
        Call call = okHttpClient.newCall(request);
        //开始请求
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //判断是否关闭加载动画，是否展示“网络异常”吐司
                        if (isShow) {
                            LoadingDialog.builder(mContext).dismiss();
                            LogUtil.e("http_tag--", url.getUrlName() + "  + 请求连接失败 :\n", e);
                            ToastUtil.showToast(R.string.MSG_NET_ERROR);
                        }

                        //接口返回请求失败
                        if (inter != null) {
                            inter.onFailure();
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String s = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //判断是否关闭加载动画
                        if (isShow) {
                            LoadingDialog.builder(mContext).dismiss();
                        }

                        try {
                            LogUtil.e("http_tag--", url.getUrlName() + "  + 请求成功 :\n" + s);

                            //判断请求是否成功，是否code == 200
                            //在这里边的getResultCode方法中已经对返回码code和信息msg进行了判断，所以具体页面使用时，无需再次判断
                            if (responseInterface.getResultCode(s, isShow)) {

                                //接口返回请求成功，回传请求结果
                                if (inter != null) {
                                    inter.onSuccess(s);
                                }

                            } else {
                                //否则表示请求失败，接口返回请求失败
                                LogUtil.e("http_tag--", url.getUrlName() + "  + 返回code异常 :\n" + s);
                                if (inter != null) {
                                    inter.onFailure();
                                }
                            }

                        } catch (Exception e) {
                            //数据解析异常，接口返回请求失败
                            LogUtil.e("http_tag--", url.getUrlName() + "  + 数据解析失败 :\n", e);
                            if (inter != null) {
                                inter.onFailure();
                            }
                        }
                    }
                });

            }
        });
    }

    /**
     * get请求，传入时间大于60秒为有效
     *
     * @param mContext 需要获取上下文
     * @param time     请求延时时间，必须大于60秒
     * @param url      网络接口枚举
     * @param isShow   判断是否展示吐司 或 加载动画
     * @param inter    回调接口
     */
    private void toGetRequest(final Context mContext, int time, final String url, final boolean isShow, final AbHttpResponseListenerInterface inter) {

        //2,创建一个Request，设定为get请求
        Request request = new Request.Builder().url(url).get().build();

        LogUtil.e("http_tag--", "  + url === : " + url);
        //2,创建一个Request
        if (isShow) {
            LoadingDialog.builder(mContext).show();
        }

        //3,创建一个call对象
        OkHttpClient okHttpClient = initOkHttpClient(time);
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isShow) {
                            LoadingDialog.builder(mContext).dismiss();
                            LogUtil.e("http_tag--", "  + 请求连接失败 :", e);
                            ToastUtil.showToast(R.string.MSG_NET_ERROR);
                        }

                        if (inter != null) {
                            inter.onFailure();
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String s = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isShow) {
                            LoadingDialog.builder(mContext).dismiss();
                        }

                        try {
                            LogUtil.e("http_tag--", "  + 请求成功 :" + s);

                            //判断请求是否成功，是否code == 200
                            //在这里边的getResultCode方法中已经对返回码code和信息msg进行了判断，所以具体页面使用时，无需再次判断
                            if (responseInterface.getResultCode(s, isShow)) {
                                if (inter != null) {
                                    inter.onSuccess(s);
                                    return;
                                }

                            } else {
                                //否则表示请求失败，接口返回请求失败
                                LogUtil.e("http_tag--", "  + 返回code异常 :" + s);
                                if (inter != null) {
                                    inter.onFailure();
                                    return;
                                }
                            }

                        } catch (Exception e) {
                            LogUtil.e("http_tag--", "  + 数据解析失败 :", e);
                            if (inter != null) {
                                inter.onFailure();
                            }
                        }
                    }
                });

            }
        });
    }

    /**
     * 获取接口回传的公用参数，并拼接到请求参数中
     *
     * @param params
     * @return
     */
    private JSONObject putDataJSON(JSONObject params) {
        Map<String, Object> map = responseInterface.addParams();

        // 获取所有的键
        Set<String> set = map.keySet();

        // 遍历键的集合，获取每一个键
        for (String key : set) {
            // 根据键去找值
            params.put(key, map.get(key));
        }

        return params;
    }

    public interface AbHttpResponseListenerInterface {

        /**
         * 接口回调时，表示请求成功
         */
        void onSuccess(String s);

        /**
         * 接口回调时，表示请求失败
         */
        void onFailure();
    }

    private static HttpResponseInterface responseInterface;

    public interface HttpResponseInterface {

        /**
         * 接口回调时，表示请求需要拼接公用参数
         */
        Map<String, Object> addParams();

        /**
         * 请求头名称
         *
         * @return
         */
        String addHeaderName();

        /**
         * 添加请求头
         *
         * @return
         */
        String addHeaderValue();

        /**
         * 接口回调时，表示请求成功，需要解析code
         */
        boolean getResultCode(String s, boolean isShow);
    }
}
