package com.berlski.tool.custom.util;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.DisplayMetrics;
import android.widget.Button;

import com.berlski.tool.custom.R;

public class UiUtil {

    /**
     * dp-->px转换
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 根据dimen值计算返回对应屏幕的px值，
     *
     * @param id R.dimen.id
     * @return
     */
    public static int getCount(Context context, int id) {
        return context.getResources().getDimensionPixelSize(id);
    }

    public static void drawableSetStyleColor(Context context, Drawable drawable) {

        //1:通过图片资源文件生成Drawable实例
        //2:先调用DrawableCompat的wrap方法
        drawable = DrawableCompat.wrap(drawable);
        //3:再调用DrawableCompat的setTint方法，为Drawable实例进行着色
        DrawableCompat.setTint(drawable, ColorUtil.getColor(context, R.color.color_styles));
    }


    public static void drawableSetColor(Context context, Drawable drawable, @ColorRes int color) {

        //1:通过图片资源文件生成Drawable实例
        //2:先调用DrawableCompat的wrap方法
        drawable = DrawableCompat.wrap(drawable);
        //3:再调用DrawableCompat的setTint方法，为Drawable实例进行着色
        DrawableCompat.setTint(drawable, ColorUtil.getColor(context, color));
    }

    public static void drawableSetColor(Drawable drawable, int color) {

        //1:通过图片资源文件生成Drawable实例
        //2:先调用DrawableCompat的wrap方法
        drawable = DrawableCompat.wrap(drawable);
        //3:再调用DrawableCompat的setTint方法，为Drawable实例进行着色
        DrawableCompat.setTint(drawable, color);
    }



    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    public static void setDrawable(Context context, Drawable drawable, int color) {
        drawableSetColor(drawable, color);
        /*DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int size;
        size = 90 * dm.heightPixels / 2560;
        drawable.setBounds(0, 0, size, size);*/
    }
}
