package com.berlski.tool.custom.util;

import android.util.Log;

import static android.util.Log.getStackTraceString;

/**
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * Log日志输出工具类（Util）
 * Created by Berlski on 2019/3/8.
 */
public class LogUtil {
    /**
     * IS_DE_BUG :是用来控制，是否打印日志
     */
    private static final boolean IS_DE_BUG = AppUtil.isDeBug();

    /**
     * tag 标识符
     */
    private static final String LOG_TAG = "LOG_TAG --：";


    /**
     * verbose等级的日志输出
     *
     * @param msg 要输出的内容
     */
    public static void v(String msg) {
        v(LOG_TAG, msg);
    }

    /**
     * verbose等级的日志输出
     *
     * @param tag 日志标识
     * @param msg 要输出的内容
     */
    public static void v(String tag, String msg) {
        // 是否开启日志输出
        if (IS_DE_BUG) {
            printlnAllLogV(tag, msg);
        }
    }

    /**
     * verbose等级的日志输出
     * 截断输出日志
     *
     * @param msg
     */
    private static void printlnAllLogV(String tag, String msg) {
        if (tag == null || tag.length() == 0 || msg == null || msg.length() == 0) {
            return;
        }

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.v(tag, msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.v(tag, logContent);
            }
            Log.v(tag, msg);// 打印剩余日志
        }
    }

    /**
     * debug等级的日志输出
     *
     * @param msg 要输出的内容
     */
    public static void d(String msg) {
        d(LOG_TAG, msg);
    }

    /**
     * debug等级的日志输出
     *
     * @param tag 日志标识
     * @param msg 要输出的内容
     */
    public static void d(String tag, String msg) {
        if (IS_DE_BUG) {
            printlnAllLogD(tag, msg);
            //Log.d(tag, msg);
        }
    }

    /**
     * debug等级的日志输出
     * 截断输出日志
     *
     * @param msg
     */
    private static void printlnAllLogD(String tag, String msg) {
        if (tag == null || tag.length() == 0 || msg == null || msg.length() == 0) {
            return;
        }

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.d(tag, msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.d(tag, logContent);
            }
            Log.d(tag, msg);// 打印剩余日志
        }
    }

    /**
     * info等级的日志输出
     *
     * @param msg 要输出的内容
     */
    public static void i(String msg) {
        i(LOG_TAG, msg);
    }

    /**
     * info等级的日志输出
     *
     * @param tag 日志标识
     * @param msg 要输出的内容
     */
    public static void i(String tag, String msg) {
        if (IS_DE_BUG) {
            printlnAllLogI(tag, msg);
            //Log.i(tag, msg);
        }
    }

    /**
     * info等级的日志输出
     * 截断输出日志
     *
     * @param msg
     */
    private static void printlnAllLogI(String tag, String msg) {
        if (tag == null || tag.length() == 0 || msg == null || msg.length() == 0) {
            return;
        }

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.i(tag, msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.i(tag, logContent);
            }
            Log.i(tag, msg);// 打印剩余日志
        }
    }

    /**
     * warn等级的日志输出
     *
     * @param msg 要输出的内容
     */
    public static void w(String msg) {
        w(LOG_TAG, msg);
    }

    /**
     * warn等级的日志输出
     *
     * @param tag 日志标识
     * @param msg 要输出的内容
     */
    public static void w(String tag, String msg) {
        if (IS_DE_BUG) {
            printlnAllLogW(tag, msg);
            //Log.w(tag, msg);
        }
    }

    /**
     * warn等级的日志输出
     * 截断输出日志
     *
     * @param msg
     */
    private static void printlnAllLogW(String tag, String msg) {
        if (tag == null || tag.length() == 0 || msg == null || msg.length() == 0) {
            return;
        }

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.w(tag, msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.w(tag, logContent);
            }
            Log.w(tag, msg);// 打印剩余日志
        }
    }

    /**
     * error等级的日志输出
     *
     * @param tr 抛出的异常
     */
    public static void w(String tag, String msg, Throwable tr) {
        w(tag, msg + '\n' + getStackTraceString(tr));
    }

    /**
     * error等级的日志输出
     *
     * @param msg 要输出的内容
     */
    public static void e(String msg) {
        e(LOG_TAG, msg);
    }

    /**
     * error等级的日志输出
     *
     * @param tr 抛出的异常
     */
    public static void e(Throwable tr) {
        e(LOG_TAG, getStackTraceString(tr));
    }

    /**
     * error等级的日志输出
     *
     * @param tr 抛出的异常
     */
    public static void e(String msg, Throwable tr) {
        e(LOG_TAG, msg + '\n' + getStackTraceString(tr));
    }

    /**
     * error等级的日志输出
     *
     * @param tr 抛出的异常
     */
    public static void e(String tag, String msg, Throwable tr) {
        e(tag, msg + '\n' + getStackTraceString(tr));
    }

    /**
     * error等级的日志输出
     *
     * @param tag 日志标识
     * @param msg 要输出的内容
     */
    public static void e(String tag, String msg) {
        if (IS_DE_BUG) {
            printlnAllLogE(tag, msg);
        }
    }

    /**
     * error等级的日志输出
     * 截断输出日志
     *
     * @param msg
     */
    private static void printlnAllLogE(String tag, String msg) {
        if (tag == null || tag.length() == 0 || msg == null || msg.length() == 0) {
            return;
        }

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.e(tag, msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// 打印剩余日志
        }
    }
}
