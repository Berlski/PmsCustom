package com.berlski.tool.custom.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import java.util.regex.Pattern;

/**
 * Created by Berlski on 2018/10/21.
 */

public class ColorUtil extends Color {

    /**
     * 获取颜色值
     *
     * @param colorRes
     * @return
     */
    public static int getColor(String colorRes) {
        return parseColor(colorRes);
    }

    /**
     * 获取颜色值
     *
     * @param context
     * @param colorRes
     * @return
     */
    public static int getColor(@NonNull Context context, @ColorRes int colorRes) {
        return ContextCompat.getColor(context, colorRes);
    }

    /**
     * 得到颜色字符串
     *
     * @param colorRes
     * @return
     */
    public static String getColorStr(@ColorRes int colorRes) {

        StringBuffer stringBuffer = new StringBuffer();
        int color = AppUtil.getResources().getColor(colorRes);
        int red = (color & 0xff0000) >> 16;
        int green = (color & 0x00ff00) >> 8;
        int blue = (color & 0x0000ff);


        stringBuffer.append(Integer.toHexString(red));
        stringBuffer.append(Integer.toHexString(green));
        stringBuffer.append(Integer.toHexString(blue));
        return stringBuffer.toString();
    }

    @ColorInt
    public static final int BLACK = 0xFF000000;
    @ColorInt
    public static final int DKGRAY = 0xFF444444;
    @ColorInt
    public static final int GRAY = 0xFF888888;
    @ColorInt
    public static final int LTGRAY = 0xFFCCCCCC;
    @ColorInt
    public static final int WHITE = 0xFFFFFFFF;
    @ColorInt
    public static final int RED = 0xFFFF0000;
    @ColorInt
    public static final int GREEN = 0xFF00FF00;
    @ColorInt
    public static final int BLUE = 0xFF0000FF;
    @ColorInt
    public static final int YELLOW = 0xFFFFFF00;
    @ColorInt
    public static final int ORANGE = 0xFFFFA500;
    @ColorInt
    public static final int CYAN = 0xFF00FFFF;
    @ColorInt
    public static final int MAGENTA = 0xFFFF00FF;
    @ColorInt
    public static final int PURPLE = 0xFF9900ff;
    @ColorInt
    public static final int TRANSPARENT = 0;

    /**
     * 设置颜色透明度
     *
     * @param color 基础色值
     * @param alpha 透明度
     * @return color
     */
    public static int getColorAlpha(int color, int alpha) {
        int red = Color.red(color);
        int green = Color.green(color);
        int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    /**
     * 将十六进制 颜色代码 转换为 int
     *
     * @return color
     */
    public static int toColorInt(String color) {
        String reg = "#[a-f0-9A-F]{8}";
        if (!Pattern.matches(reg, color)) {
            color = "#ffffffff";
        }
        return Color.parseColor(color);
    }
}
