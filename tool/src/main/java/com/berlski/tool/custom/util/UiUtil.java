package com.berlski.tool.custom.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.TextView;

import com.berlski.tool.custom.R;

public class UiUtil {

    /**
     * 给drawable重设颜色，颜色固定为统一颜色样式
     *
     * @param context
     * @param drawable
     */
    public static void drawableSetStyleColor(Context context, Drawable drawable) {

        //1:通过图片资源文件生成Drawable实例
        //2:先调用DrawableCompat的wrap方法
        drawable = DrawableCompat.wrap(drawable);
        //3:再调用DrawableCompat的setTint方法，为Drawable实例进行着色
        DrawableCompat.setTint(drawable, ColorUtil.getColor(context, R.color.color_styles));
    }

    /**
     * 给drawable重设颜色
     *
     * @param context
     * @param drawable
     * @param color     限定传入为 ColorRes
     */
    public static void drawableSetColor(Context context, Drawable drawable, @ColorRes int color) {

        //1:通过图片资源文件生成Drawable实例
        //2:先调用DrawableCompat的wrap方法
        drawable = DrawableCompat.wrap(drawable);
        //3:再调用DrawableCompat的setTint方法，为Drawable实例进行着色
        DrawableCompat.setTint(drawable, ColorUtil.getColor(context, color));
    }

    /**
     * 给drawable重设颜色
     *
     * @param drawable
     * @param color
     */
    public static void drawableSetColor(Drawable drawable, int color) {

        //1:通过图片资源文件生成Drawable实例
        //2:先调用DrawableCompat的wrap方法
        drawable = DrawableCompat.wrap(drawable);
        //3:再调用DrawableCompat的setTint方法，为Drawable实例进行着色
        DrawableCompat.setTint(drawable, color);
    }

    /**
     * 给textView设定右侧Drawable
     *
     * @param textView
     * @param drawableRes
     * @param id
     * @return
     */
    public static Drawable setTextRightDrawable(TextView textView, @DrawableRes int drawableRes, @DimenRes int id) {

        Drawable drawable = textView.getContext().getResources().getDrawable(drawableRes);

        drawable.setBounds(0, 0, DensityUtils.getCount(textView.getContext(), id), DensityUtils.getCount(textView.getContext(), id));

        //设定图片
        textView.setCompoundDrawables(null, null, drawable, null);

        return drawable;
    }

    /**
     * 给条目统一设定必选标识
     *
     * @param textView
     */
    public static void setRequiredMarkerLabel(TextView textView) {
        Drawable drawable = setTextRightDrawable(textView, R.drawable.ic_stars, R.dimen.dp10);

        //设定图片边距
        textView.setCompoundDrawablePadding(DensityUtils.getCount(textView.getContext(), R.dimen.dp10));

        drawableSetStyleColor(textView.getContext(), drawable);
    }
}
