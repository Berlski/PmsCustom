package com.berlski.tool.custom.util;

import android.content.Context;
import android.graphics.Color;
import android.os.Looper;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.berlski.tool.custom.R;

/**
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 吐司工具类（Util）
 * Created by Berlski on 2018/10/16.
 */
public class ToastUtil {

    private static Context getContext() {
        return AppUtil.getContext();
    }

    /**
     * 吐司
     *
     * @param text
     */
    public static void showToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 吐司
     *
     * @param text
     */
    public static void showToast(int text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 吐司
     *
     * @param text
     */
    public static void longToast(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_LONG).show();
    }

    /**
     * @param text
     */
    public static void centerToast(String text) {
        Toast toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * @param text
     */
    public static void centerToast(int text) {
        Toast toast = Toast.makeText(getContext(), text, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    /**
     * @param
     */
    public static void showToasts(final String toast) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                Looper.prepare();
                showToast(toast);
                Looper.loop();
            }
        }).start();
    }


    /**
     * 在屏幕中间显示吐司
     *
     * @param message
     */
    public void toastCentent(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        TextView textView = new TextView(getContext());
        textView.setPadding(180, 60, 180, 60);
        textView.setText(message);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.parseColor("#ffffff"));
        textView.setBackgroundResource(R.drawable.toast_background);
        toast.setView(textView);
        toast.show();
    }

    /**
     * 在屏幕中间显示吐司
     *
     * @param message
     */
    public static void longToastCentent(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        TextView textView = new TextView(getContext());
        textView.setPadding(180, 60, 180, 60);
        textView.setText(message);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.parseColor("#ffffff"));
        textView.setBackgroundResource(R.drawable.toast_background);
        toast.setView(textView);
        toast.show();
    }

    /**
     * 在屏幕中间显示吐司
     *
     * @param message
     */
    public static void longToastBottom(Context context, String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM, 0, UiUtil.getCount(context, R.dimen.dp50));
        TextView textView = new TextView(getContext());
        textView.setPadding(180, 60, 180, 60);
        textView.setText(message);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.parseColor("#ffffff"));
        textView.setBackgroundResource(R.drawable.toast_background);
        toast.setView(textView);
        toast.show();
    }

    /**
     * 在屏幕底部显示吐司
     *
     * @param message
     */
    public void toastBottom(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    /**
     * 在屏幕上方显示吐司
     *
     * @param message
     */
    public void toastTop(String message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }

    /**
     * 在屏幕中间显示吐司
     *
     * @param message
     */
    public void toastCentent(int message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 在屏幕底部显示吐司
     *
     * @param message
     */
    public void toastBottom(int message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }

    /**
     * 在屏幕上方显示吐司
     *
     * @param message
     */
    public void toastTop(int message) {
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }
}
