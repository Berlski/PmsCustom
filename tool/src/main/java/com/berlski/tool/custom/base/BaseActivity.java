package com.berlski.tool.custom.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.berlski.tool.custom.manager.DialogManager;
import com.berlski.tool.custom.util.NetUtil;
import com.berlski.tool.custom.util.ToastUtil;
import com.berlski.tool.custom.util.UiUtil;

import java.util.List;

/**
 * 所有activity的基类
 * Created by Administrator on 2017/3/22.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 当前avtivity上下文
     */
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this.getApplicationContext();
        superInitView(savedInstanceState);
        initData();
        initOnclickListenter();
    }


    private void superInitView(Bundle savedInstanceState) {
        //super.setContentView(R.layout.);

        //为继承了BasePageActivity的类禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    //继承了BaseActivity的页面需要实现
    protected abstract void initView(Bundle savedInstanceState);

    //继承了BaseActivity的页面需要实现
    protected abstract void initTitleBar();

    //继承了BaseActivity的页面需要实现
    protected abstract void initData();

    //继承了BaseActivity的页面需要实现
    protected abstract void initOnclickListenter();

    //继承了BaseActivity的页面需要实现
    protected abstract void OnKeyDown(int keyCode, KeyEvent event);

    /**
     * 手机返回键调用方法
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            OnKeyDown(keyCode, event);
        }

        //finish();
        return false;
    }

    /**
     * getView
     *
     * @param id
     * @return
     */
    protected View gV(int id) {
        return findViewById(id);
    }

    /**
     * getViewPager
     *
     * @param id
     * @return
     */
    protected ViewPager gVP(int id) {
        return (ViewPager) findViewById(id);
    }

    //获取手机状态栏的高度
    private int getStatusBarHeightInt() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取手机分辨率  和唯一标示
     */
    private void getPhoneSize() {
        final PackageManager packageManager = this.getPackageManager();
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        final List<ResolveInfo> apps = packageManager.queryIntentActivities(mainIntent, 0);

        // 方法1 Android获得屏幕的宽和高
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();
        int screenHeight = display.getHeight();

        //将宽高存储到常量类
        //Constants.SCREEN_WIDTH = screenWidth;
        //Constants.SCREEN_HEIGHT = screenHeight;
    }


    /**
     * 将资源文件中定义的dip值乘以屏幕密度
     *
     * @param id
     * @return
     */
    public int getCount(int id) {
        return getResources().getDimensionPixelSize(id);
    }

    /**
     * 判断网络是否连接
     *
     * @return
     */
    public boolean isNetworkAvailable() {
        return NetUtil.isNetworkAvailable();
    }

    /**
     * 状态栏的隐藏和显现
     *
     * @param enable
     */
    public void fullscreenActivity(boolean enable) {

        if (enable) { //显示状态栏
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;

            getWindow().setAttributes(lp);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        } else { //隐藏状态栏

            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);

            getWindow().setAttributes(lp);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    public int dip2px(Context context, float dpValue) {
        return UiUtil.dip2px(dpValue);
    }

    public void showToast(String toast) {
        ToastUtil.showToast(toast);
    }

    public void startLoading() {
        DialogManager.showLoadingDialog();//startLoading(mContext);
    }

    public void cancelLoading() {
        DialogManager.dismissLoadingDialog();//Utils.cancelLoading();
    }
}
