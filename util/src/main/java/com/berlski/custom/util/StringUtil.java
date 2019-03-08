package com.berlski.custom.util;

import java.math.BigDecimal;

/**
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 字符串工具类（Util）
 * Created by Berlski on 2019/3/8.
 */
public class StringUtil {

    public static boolean indexOf(String parent, String child) {

        if (parent.indexOf(child) != -1) {
            return true;
        }
        return false;
    }

    /**
     * @param parent
     * @param child
     * @param indext
     * @return
     */
    public static String splitAssign(String parent, String child, int indext) {
        String[] str = (parent.split("="));
        String text = "";

        if (indext == 0) {

            text = str[indext];

        } else if (indext > 0) {

            text = str[str.length - 1];
        }

        return text;
    }


    /**
     * 判断字符串是否非空
     *
     * @param s 字符串
     * @return 如果是非空返回true
     */
    public static boolean isNotEmpty(String s) {

        if (!isEmpty(s)) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否是空
     *
     * @param s 字符串
     * @return 如果是空返回true
     */
    public static boolean isEmpty(String s) {
        if (null == s) {
            return true;
        }
        if (s.length() == 0) {
            return true;
        }
        if (s.trim().length() == 0) {
            return true;
        }
        return false;
    }


    /**
     * 通过身份证号，判断性别，返回性别及tag
     *
     * @param idCard
     * @return
     */
    public static String[] getSex(String idCard) {

        String[] sex = new String[2];

        if (idCard.length() == 15 || idCard.length() == 18) {
            String sexCode = "";
            //18位身份证号，截取倒数第二位，
            //15位身份证号，截取倒数第一位，
            if (idCard.length() == 15) {
                sexCode = idCard.substring(14, 15);
            } else {
                sexCode = idCard.substring(16, 17);
            }

            //截取的字符转为数字，模2等于0表示偶数，为女，否则奇数为男
            if (Integer.parseInt(sexCode) % 2 == 0) {
                sex[0] = "女";
                sex[1] = "0";

            } else {
                sex[0] = "男";
                sex[1] = "1";
            }
        } else {
            sex[0] = "";
            sex[1] = "";
        }

        return sex;
    }

    public static String returnsPhotoUrlFileName(String str) {

        String result = "";

        //包含
        if (str.indexOf(".com/") != -1) {

            String[] strs = str.split(".com/");

            //截取N中，第0个位置到最后一个f之前的全部字符;
            return (strs[1]).substring(0, strs[1].lastIndexOf("."));

            //不包含
        } else if (str.indexOf("/") != -1) {

            String[] strs = str.split(".com/");
            return strs[strs.length - 1];
        }

        return result;
    }

    public static boolean isUrl(String str) {

        //不是空           不是空字符串                  包含http://
        if (isNotEmpty(str) && str.indexOf("http://") != -1) {
            return true;

            //不包含
        } else {
            return false;
        }
    }

    //格式化 电子化移交完成率 保留两位
    public static String formateRate(String rateStr) {
        rateStr = rateStr.trim();

        if (rateStr.indexOf(".") != -1) {
            //获取小数点的位置
            int num = 0;
            num = rateStr.indexOf(".");

            //获取小数点后面的数字 是否有两位 不足两位补足两位
            String dianAfter = rateStr.substring(0, num + 1);
            String afterData = rateStr.replace(dianAfter, "");
            if (afterData.length() < 2) {
                afterData = afterData + "0";
            }
            return rateStr.substring(0, num) + "." + afterData.substring(0, 2);
        } else {
            return rateStr + ".00";
        }
    }

    //格式化 电子化移交完成率 不保留两位
    public static String formateInteger(String rateStr) {
        rateStr = rateStr.trim();

        if (rateStr.indexOf(".") != -1) {
            //获取小数点的位置
            int num = 0;
            num = rateStr.indexOf(".");

            //获取小数点后面的数字 是否有两位 不足两位补足两位
            String dianAfter = rateStr.substring(0, num + 1);
            String afterData = rateStr.replace(dianAfter, "");
            if (afterData.length() < 2) {
                afterData = afterData + "0";
            }
            return rateStr.substring(0, num);
        }

        return rateStr;
    }

    /**
     * 四舍五入保留一位小数
     *
     * @param f
     * @return
     */
    public static double oneFormateInteager(int wei, double f) {

        // 方式一：
        BigDecimal b = new BigDecimal(f);
        return b.setScale(wei, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 判断一个字符串中是否包含有Emoji表情
     *
     * @param input
     * @return true 有Emoji
     */
    public static boolean isEmojiCharacter(CharSequence input) {
        for (int i = 0; i < input.length(); i++) {
            if (isEmojiCharacter(input.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检测是否有emoji字符
     *
     * @param input
     * @return FALSE，包含图片
     */
    public static boolean isEmojiCharacter(String input) {
        if (isEmpty(input)) {
            return false;
        }

        for (int i = 0; i < input.length(); i++) {
            if (isEmojiCharacter(input.charAt(i))) {
                //do nothing，判断到了这里表明，确认有表情字符
                return true;
            }
        }

        return false;
    }

    /**
     * 去除字符串中的Emoji表情
     *
     * @param source
     * @return
     */
    public static String removeEmoji(CharSequence source) {
        String result = "";
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            if (isEmojiCharacter(c)) {
                continue;
            }
            result += c;
        }
        return result;
    }

    /**
     * 是否是Emoji 表情,抄的那哥们的代码
     *
     * @param codePoint
     * @return true 是Emoji表情
     */
    /*public static boolean isEmojiCharacter(char codePoint) {
        // Emoji 范围
        boolean isScopeOf = (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF) && (codePoint != 0x263a)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));

        return !isScopeOf;
    }*/

    /**
     * 是否是Emoji 表情,抄的那哥们的代码
     *
     * @param codePoint
     * @return true 是Emoji表情
     */
    private static boolean isEmojiCharacter(char codePoint) {
        // Emoji 范围
        boolean isScopeOf = (codePoint == 0x0) ||
                (codePoint == 0x9) ||
                (codePoint == 0xA) ||
                (codePoint == 0xD) ||
                ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||
                ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));

        return !isScopeOf;
    }

    /**
     * 创建指定数量的随机字符串
     *
     * @param numberFlag 是否是数字
     * @param length
     * @return
     */
    public static String getRandomCode(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);

        return retStr;
    }
}
