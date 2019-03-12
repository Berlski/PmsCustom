package com.berlski.tool.custom.util;

public class UiUtil {

    /**
     * dp-->px转换
     */
    public static int dip2px(float dpValue) {
        final float scale = AppUtil.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }


    /**
     * 根据dimen值计算返回对应屏幕的px值，
     *
     * @param id R.dimen.id
     * @return
     */
    public static int getCount(int id) {
        return AppUtil.getResources().getDimensionPixelSize(id);
    }
}
