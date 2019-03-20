package com.berlski.tool.custom.inter;

/**
 * 数字区间回调接口
 */
public interface NumberRangeInter {

    /**
     * 回传数字区间
     *
     * @param minNum 最大数字
     * @param maxNum 最大数字
     */
    void onSelect(String minNum, String maxNum);

    /**
     * 取消了选择
     */
    void onCancel();
}
