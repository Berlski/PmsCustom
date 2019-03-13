package com.berlski.tool.custom.bean;

public class zhengze {

    /** 验证是否为EMAIL格式 */
    public static final String EMAIL = "('')|(\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*)";

    /** 验证电话号码 */
    public static final String TELEPHONE = "('')|(\\d{4}(-*)\\d{8}|\\d{4}(-*)\\d{7}|\\d{3}(-*)\\d{8}|\\d{3}(-*)\\d{7})";

    /** 验证手机号码 */
    public static final String MOBILEPHONE = "1(3|5|8|7)\\d{9}";// "[1][3|5|8]+\\d{9}";

    /** 验证是否是电话或者手机号码 */
    public static final String TELEMOBILE = "^((\\d{3,4}?-|\\(\\d{3,4}\\))?\\d{8,11}$)|(^0{0,1}13[0-9]{9}$)";

    /** 是否全部为中文 */
    public static final String CHINESECHAR = "^[\u4e00-\u9fa5]+$";

    /** 检查字符串中是否还有HTML标签 */
    public static final String HTMLTAGHAS = "<(\\S*?)[^>]*>.*?</\\1>|<.*? />";

    /** 检查URL是否合法 */
    public static final String URL = "[a-zA-z]+://[^\\s]*";

    /** 检查IP是否合法 */
    public static final String IPADRESS = "\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}";

    /** 检查QQ号是否合法 */
    public static final String QQCODE = "[1-9][0-9]{4,13}";

    /** 检查邮编是否合法 */
    public static final String POSTCODE = "[1-9]\\d{5}(?!\\d)";

    /** 正整数 */
    public static final String POSITIVE_INTEGER = "^[0-9]\\d*$";

    /** 正浮点数 */
    public static final String POSITIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[0-9]\\d*$";

    /** 整数或小数 */
    public static final String POSITIVE_DOUBLE = "^[0-9]+(\\.[0-9]+)?$";

    /** 年月日 2012-1-1,2012/1/1,2012.1.1 */
    public static final String DATE_YMD = "^\\d{4}(\\-|\\/|.)\\d{1,2}\\1\\d{1,2}$";

    /** 检查身份证是否合法 验证时请先验证长度是否为15为或者18位 */
    public static final String IDCARD = "\\d{6}(19|20)*[0-99]{2}(0[1-9]{1}|10|11|12)(0[1-9]{1}"
            + "|1[0-9]|2[0-9]|30|31)(\\w*)";

    /** 检查护照是否合法 */
    public static final String PASSPORT1 = "/^[a-zA-Z]{5,17}$/";
    public static final String PASSPORT2 = "/^[a-zA-Z0-9]{5,17}$/";

    /** 港澳通行证验证   */
    public static final String HKMAKAO = "/^[HMhm]{1}([0-9]{10}|[0-9]{8})$/";

    /** 台湾通行证验证   */
    public static final String TAIWAN1 = " /^[0-9]{8}$/";
    public static final String TAIWAN2 = "/^[0-9]{10}$/";


}
