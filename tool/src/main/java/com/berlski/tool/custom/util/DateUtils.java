package com.berlski.tool.custom.util;

import com.berlski.tool.custom.enums.DateName;
import com.berlski.tool.custom.enums.DateStyleEnum;
import com.berlski.tool.custom.enums.Week;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * 此代码属于数据与信息中心部门编写，在未经允许的情况下不得传播复制
 * 时间工具类（Util）
 * Created by Berlski on 2019/3/8.
 */
public class DateUtils {

    /**
     * 将字符串转为时间戳
     *
     * @param time
     * @return
     */
    public static String getTimeToStamp(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒",
                Locale.CHINA);
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String tmptime = String.valueOf(date.getTime()).substring(0, 10);

        return tmptime;
    }

    /**
     * 获取当前日期 / 时间，需要日期 / 时间类型
     *
     * @param style
     * @return
     */
    public static String getThis(String style) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(style);
            Date curDate = new Date(System.currentTimeMillis());//获取当前年月日
            return formatter.format(curDate);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取当前日期 / 时间，需要日期 / 时间类型
     *
     * @param style
     * @return
     */
    public static String getThis(DateStyleEnum style) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(style.getValue());
            Date curDate = new Date(System.currentTimeMillis());//获取当前年月日
            return formatter.format(curDate);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取当前时间，固定时间格式为  "yyyy-MM-dd HH:mm:ss"
     *
     * @return
     */
    public static String getThis() {
        return getThis(DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getThisDate() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date curDate = new Date(System.currentTimeMillis());//获取当前年月日
            return formatter.format(curDate);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getThisTime() {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            Date curDate = new Date(System.currentTimeMillis());//获取当前精确时间
            return formatter.format(curDate);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取星期几
     *
     * @return
     */
    public static String getWeek(boolean choose) {
        String week = "";//星期
        String name = choose == true ? "星期" : "周";
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        week = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        if ("1".equals(week)) {
            week = name + (choose == true ? "天" : "日");
        } else if ("2".equals(week)) {
            week = name + "一";
        } else if ("3".equals(week)) {
            week = name + "二";
        } else if ("4".equals(week)) {
            week = name + "三";
        } else if ("5".equals(week)) {
            week = name + "四";
        } else if ("6".equals(week)) {
            week = name + "五";
        } else if ("7".equals(week)) {
            week = name + "六";
        }
        return week;
    }

    /**
     * 判断是否为昨天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsYesterday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat("yyyy-MM-dd HH:mm:ss").parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为今天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsToday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat("yyyy-MM-dd HH:mm:ss").parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR);

            if (0 != diffDay) {
                return false;
            }
        }
        return true;
    }


    /**
     * 获取当前时间本周的第一天
     *
     * @return
     */
    public static String getFirstDayOfWeek() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, 1);
        cal.getTime();

        return dateFormater.format(cal.getTime());
    }

    /**
     * 获取当前时间本周的最后一天
     *
     * @return
     */
    public static String getLastDayOfWeek() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getActualMaximum(Calendar.DAY_OF_WEEK));

        return dateFormater.format(cal.getTime());
    }

    /**
     * 获取当前时间本月的第一天
     *
     * @return
     */
    public static String getFirstDayOfMonth() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.getTime();

        return dateFormater.format(cal.getTime());
    }

    /**
     * 获取当前时间本月的最后一天
     *
     * @return
     */
    public static String getLastDayOfMonth() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        return dateFormater.format(cal.getTime());
    }

    /**
     * 获取当前时间本年的第一天
     *
     * @return
     */
    public static String getFirstDayOfYear() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, 1);
        cal.getTime();

        return dateFormater.format(cal.getTime());
    }

    /**
     * 获取当前时间本年的最后一天
     *
     * @return
     */
    public static String getLastDayOfYear() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_YEAR, cal.getActualMaximum(Calendar.DAY_OF_YEAR));

        return dateFormater.format(cal.getTime());
    }

    /**
     * 获取当前月的最后一天
     */
    public static StringBuilder getLastDayOfMonth_() {
        Calendar c = Calendar.getInstance(); // 当时的日期和时间

        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = getMonthDay(mYear, mMonth);
        StringBuilder strForwardDate = new StringBuilder()
                .append(mYear)
                .append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                .append("-")
                .append((mDay < 10) ? "0" + mDay : mDay);

        return strForwardDate;
    }

    /**
     * 获取退后天数的日期
     *
     * @param time
     */
    public static StringBuilder getAfterDayDate(int time) {
        Calendar c = Calendar.getInstance(); // 当时的日期和时间
        int day; // 需要更改的天数

        day = c.get(Calendar.DAY_OF_MONTH) + time;
        c.set(Calendar.DAY_OF_MONTH, day);

        System.out.println("strDate------->" + getAppendTime(c) + "-" + c.getTimeInMillis());
        return getAppendTime(c);
    }

    /**
     * 获取退后天数的日期
     *
     * @param time
     */
    public static StringBuilder getAfterDayDate(String data, int time) {
        int day = 0;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            data = getString(data, "yyyy-MM-dd HH:mm:ss");
            // 用parse方法，可能会异常，所以要try-catch
            Date date = format.parse(data);
            // 获取日期实例
            Calendar calendar = Calendar.getInstance();
            // 将日历设置为指定的时间
            calendar.setTime(date);

            // 获取天
            day = calendar.get(Calendar.DAY_OF_MONTH) + time;

            //------------------------------------------------------------------------------------------------

            //day = c.get(Calendar.DAY_OF_MONTH) + time;
            calendar.set(Calendar.DAY_OF_MONTH, day);

            System.out.println("strDate------->" + getAppendTime(calendar) + "-" + calendar.getTimeInMillis());
            return getAppendTime(calendar);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int CompareDateSize(String date1, String date2) {

        try {
            date1 = getString(date1, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
            date2 = getString(date2, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);

            long longdate1 = Long.valueOf(date1.replaceAll("[-\\s:]", ""));
            long longdate2 = Long.valueOf(date2.replaceAll("[-\\s:]", ""));

            if (longdate1 < longdate2) {
                return 1;
            } else if (longdate1 > longdate2) {
                return 0;
            }
        } catch (Exception e) {

        }
        return -1;
    }

    /**
     * 传入时间字符串，获取时间字符串中的年
     *
     * @param dateStr
     * @return
     */
    public int getDateYearCount(String dateStr) {
        // 需要解析的日期字符串
        //dateStr = "2015-09-27 12:15:31";
        // 解析格式，yyyy表示年，MM(大写M)表示月,dd表示天，HH表示小时24小时制，小写的话是12小时制
        int year = 0;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateStr = getString(dateStr, "yyyy-MM-dd HH:mm:ss");
            // 用parse方法，可能会异常，所以要try-catch
            Date date = format.parse(dateStr);
            // 获取日期实例
            Calendar calendar = Calendar.getInstance();
            // 将日历设置为指定的时间
            calendar.setTime(date);
            // 获取年
            year = calendar.get(Calendar.YEAR);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return year;
    }

    public static int getMonthOfThis() {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        return DateUtils.getDateMonthCount(dateFormater.format(cal.getTime()));
    }

    /**
     * 传入时间字符串，获取时间字符串中的月
     *
     * @param dateStr
     * @return
     */
    public static int getDateMonthCount(String dateStr) {
        // 需要解析的日期字符串
        //dateStr = "2015-09-27 12:15:31";
        // 解析格式，yyyy表示年，MM(大写M)表示月,dd表示天，HH表示小时24小时制，小写的话是12小时制
        int month = 0;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateStr = getString(dateStr, "yyyy-MM-dd HH:mm:ss");
            // 用parse方法，可能会异常，所以要try-catch
            Date date = format.parse(dateStr);
            // 获取日期实例
            Calendar calendar = Calendar.getInstance();
            // 将日历设置为指定的时间
            calendar.setTime(date);

            // 这里要注意，月份是从0开始。
            month = calendar.get(Calendar.MONTH) + 1;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return month;
    }

    /**
     * 传入时间字符串，获取时间字符串中的日
     *
     * @param dateStr
     * @return
     */
    public int getDateDayCount(String dateStr) {
        // 需要解析的日期字符串
        //dateStr = "2015-09-27 12:15:31";
        // 解析格式，yyyy表示年，MM(大写M)表示月,dd表示天，HH表示小时24小时制，小写的话是12小时制
        int day = 0;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateStr = getString(dateStr, "yyyy-MM-dd HH:mm:ss");
            // 用parse方法，可能会异常，所以要try-catch
            Date date = format.parse(dateStr);
            // 获取日期实例
            Calendar calendar = Calendar.getInstance();
            // 将日历设置为指定的时间
            calendar.setTime(date);

            // 获取天
            day = calendar.get(Calendar.DAY_OF_MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 获取退后小时的日期
     *
     * @param time
     */
    public static StringBuilder getAfterHourDate(int time) {
        Calendar c = Calendar.getInstance(); // 当时的日期和时间
        int hour; // 需要更改的小时

        hour = c.get(Calendar.HOUR_OF_DAY) + time;
        c.set(Calendar.HOUR_OF_DAY, hour);

        System.out.println("strDate------->" + getAppendTime(c) + "-" + c.getTimeInMillis());
        return getAppendTime(c);
    }

    /**
     * 获取提前小时的日期
     *
     * @param time
     */
    public static StringBuilder getBeforeHourDate(int time) {
        Calendar c = Calendar.getInstance(); // 当时的日期和时间
        int hour; // 需要更改的小时

        hour = c.get(Calendar.HOUR_OF_DAY) - time;
        c.set(Calendar.HOUR_OF_DAY, hour);

        System.out.println("strDate------->" + getAppendTime(c) + "-" + c.getTimeInMillis());
        return getAppendTime(c);
    }

    /**
     * 获取提前天数的日期
     *
     * @param time
     */
    public static StringBuilder getBeforeDayDate(int time) {
        Calendar c = Calendar.getInstance(); // 当时的日期和时间
        int day; // 需要更改的天数

        day = c.get(Calendar.DAY_OF_MONTH) - time;
        c.set(Calendar.DAY_OF_MONTH, day);

        System.out.println("strDate------->" + getAppendTime(c) + "-" + c.getTimeInMillis());
        return getAppendTime(c);
    }

    private static StringBuilder getAppendTime(Calendar c) {
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);
        int mSecond = c.get(Calendar.SECOND);
        StringBuilder strForwardDate = new StringBuilder()
                .append(mYear)
                .append("-")
                .append((mMonth + 1) < 10 ? "0" + (mMonth + 1) : (mMonth + 1))
                .append("-")
                .append((mDay < 10) ? "0" + mDay : mDay)
                .append(" ")
                .append((mHour < 10) ? "0" + mHour : mHour)
                .append(":")
                .append((mMinute < 10) ? "0" + mMinute : mMinute)
                .append(":")
                .append((mSecond < 10) ? "0" + mSecond : mSecond);

        return strForwardDate;
    }

    /**
     * 将日期信息转换成今天、明天、后天、星期
     *
     * @param date
     * @return
     */
    public static String getDateDetail(String date) {
        Calendar today = Calendar.getInstance();
        Calendar target = Calendar.getInstance();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            today.setTime(df.parse(getCurrentDate()));
            today.set(Calendar.HOUR, 0);
            today.set(Calendar.MINUTE, 0);
            today.set(Calendar.SECOND, 0);
            target.setTime(df.parse(getString(date, "yyyy-MM-dd HH:mm:ss")));
            target.set(Calendar.HOUR, 0);
            target.set(Calendar.MINUTE, 0);
            target.set(Calendar.SECOND, 0);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        long intervalMilli = target.getTimeInMillis() - today.getTimeInMillis();
        int xcts = (int) (intervalMilli / (24 * 60 * 60 * 1000));
        return showDateDetail(xcts, target);
    }

    /**
     * 将日期差显示为日期或者星期
     *
     * @param xcts
     * @param target
     * @return
     */
    private static String showDateDetail(int xcts, Calendar target) {
        switch (xcts) {
            case 0:
                return DateName.TODAY.getDateName();
            case 1:
                return DateName.TOMORROW.getDateName();
            case 2:
                return DateName.AFTER_TOMORROW.getDateName();
            case -1:
                return DateName.YESTERDAY.getDateName();
            case -2:
                return DateName.BEFORE_YESTERDAY.getDateName();
            default:
                int dayForWeek = 0;
                dayForWeek = target.get(Calendar.DAY_OF_WEEK);
                switch (dayForWeek) {
                    case 1:
                        return DateName.SUNDAY.getDateName();
                    case 2:
                        return DateName.MONDAY.getDateName();
                    case 3:
                        return DateName.TUESDAY.getDateName();
                    case 4:
                        return DateName.WEDNESDAY.getDateName();
                    case 5:
                        return DateName.THURSDAY.getDateName();
                    case 6:
                        return DateName.FRIDAY.getDateName();
                    case 7:
                        return DateName.SATURDAY.getDateName();
                    default:
                        return null;
                }
        }
    }

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();
    private static final Object object = new Object();

    /**
     * 转换为Calendar
     *
     * @return Calendar
     */
    private static Calendar toCalendar() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar;
    }

    private static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 设置时间
     *
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static Calendar setCalendar(int year, int month, int date) {
        Calendar cl = Calendar.getInstance();
        cl.set(year, month - 1, date);
        return cl;
    }

    /**
     * Calendar 转化 String
     * str : yyyy-MM-dd
     */
    public static String strToCalendar(Calendar c1) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(c1);
        return dateStr;
    }

    /**
     * String 转化 Calendar
     * str : yyyy-MM-dd
     */
    public static Calendar calendarToStr(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 时间格式转换
     *
     * @param date
     * @return
     */
    public static String toFormatTime(Date date, String style) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat(style);               //
        return format.format(date);
    }

    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }


    /**
     * 获取SimpleDateFormat
     *
     * @param pattern 日期格式
     * @return SimpleDateFormat对象
     * @throws RuntimeException 异常：非法日期格式
     */
    private static SimpleDateFormat getDateFormat(String pattern) throws RuntimeException {
        SimpleDateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            synchronized (object) {
                if (dateFormat == null) {
                    dateFormat = new SimpleDateFormat(pattern);
                    dateFormat.setLenient(false);
                    threadLocal.set(dateFormat);
                }
            }
        }
        dateFormat.applyPattern(pattern);
        return dateFormat;
    }

    /**
     * 获取当前日期 yyyy-MM-dd HH:mm:ss
     *
     * @return String 当前日期
     */
    public static String getCurrentDate() {
        return getString(new Date(), DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取当前日期
     *
     * @param //dateType 日期格式
     * @return String 当前日期
     */
    public static String getCurrentDate(DateStyleEnum dateStyleEnum) {
        return getString(new Date(), dateStyleEnum.getValue());
    }

    /**
     * 获取日期中的某数值。如获取月份
     *
     * @param date     日期
     * @param dateType 日期格式
     * @return 数值
     */
    private static int getInteger(Date date, int dateType) {
        int num = 0;
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
            num = calendar.get(dateType);
        }
        return num;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期字符串
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期字符串
     */
    private static String addInteger(String date, int dateType, int amount) {
        String dateString = null;
        DateStyleEnum dateStyleEnum = getDateStyle(date);
        if (dateStyleEnum != null) {
            Date myDate = getDate(date, dateStyleEnum);
            myDate = addInteger(myDate, dateType, amount);
            dateString = getString(myDate, dateStyleEnum);
        }
        return dateString;
    }

    public static String getCurTime() {
        return getString(new Date(), DateStyleEnum.YYYY_MM_DD_DH);
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期
     */
    private static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    /**
     * 获取精确的日期
     *
     * @param timestamps 时间long集合
     * @return 日期
     */
    private static Date getAccurateDate(List<Long> timestamps) {
        Date date = null;
        long timestamp = 0;
        Map<Long, long[]> map = new HashMap<Long, long[]>();
        List<Long> absoluteValues = new ArrayList<Long>();

        if (timestamps != null && timestamps.size() > 0) {
            if (timestamps.size() > 1) {
                for (int i = 0; i < timestamps.size(); i++) {
                    for (int j = i + 1; j < timestamps.size(); j++) {
                        long absoluteValue = Math.abs(timestamps.get(i) - timestamps.get(j));
                        absoluteValues.add(absoluteValue);
                        long[] timestampTmp = {timestamps.get(i), timestamps.get(j)};
                        map.put(absoluteValue, timestampTmp);
                    }
                }

                // 有可能有相等的情况。如2012-11和2012-11-01。时间戳是相等的。此时minAbsoluteValue为0
                // 因此不能将minAbsoluteValue取默认值0
                long minAbsoluteValue = -1;
                if (!absoluteValues.isEmpty()) {
                    minAbsoluteValue = absoluteValues.get(0);
                    for (int i = 1; i < absoluteValues.size(); i++) {
                        if (minAbsoluteValue > absoluteValues.get(i)) {
                            minAbsoluteValue = absoluteValues.get(i);
                        }
                    }
                }

                if (minAbsoluteValue != -1) {
                    long[] timestampsLastTmp = map.get(minAbsoluteValue);

                    long dateOne = timestampsLastTmp[0];
                    long dateTwo = timestampsLastTmp[1];
                    if (absoluteValues.size() > 1) {
                        timestamp = Math.abs(dateOne) > Math.abs(dateTwo) ? dateOne : dateTwo;
                    }
                }
            } else {
                timestamp = timestamps.get(0);
            }
        }

        if (timestamp != 0) {
            date = new Date(timestamp);
        }
        return date;
    }

    /**
     * 判断字符串是否为日期字符串
     *
     * @param date 日期字符串
     * @return true or false
     */
    public static boolean isDate(String date) {
        boolean isDate = false;
        if (date != null) {
            if (getDateStyle(date) != null) {
                isDate = true;
            }
        }
        return isDate;
    }

    /**
     * 获取日期字符串的日期风格。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期风格
     */
    public static DateStyleEnum getDateStyle(String date) {
        DateStyleEnum dateStyleEnum = null;
        Map<Long, DateStyleEnum> map = new HashMap<Long, DateStyleEnum>();
        List<Long> timestamps = new ArrayList<Long>();
        for (DateStyleEnum style : DateStyleEnum.values()) {
            if (style.isShowOnly()) {
                continue;
            }
            Date dateTmp = null;
            if (date != null) {
                try {
                    ParsePosition pos = new ParsePosition(0);
                    dateTmp = getDateFormat(style.getValue()).parse(date, pos);
                    if (pos.getIndex() != date.length()) {
                        dateTmp = null;
                    }
                } catch (Exception e) {
                }
            }
            if (dateTmp != null) {
                timestamps.add(dateTmp.getTime());
                map.put(dateTmp.getTime(), style);
            }
        }
        Date accurateDate = getAccurateDate(timestamps);
        if (accurateDate != null) {
            dateStyleEnum = map.get(accurateDate.getTime());
        }
        return dateStyleEnum;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date 日期字符串
     * @return 日期
     */
    public static Date getDate(String date) {
        return getDate(date, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
    }

    public static Date getDate2(String date) {
        return getDate(date, DateStyleEnum.YYYY_MM_DD_DH);
    }

    /**
     * 获取日期。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期
     * @return 日期
     */
    public static String getDate(Date date) {
        return getString(date, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date    日期字符串
     * @param pattern 日期格式
     * @return 日期
     */
    public static Date getDate(String date, String pattern) {
        Date myDate = null;
        if (date != null) {
            try {
                myDate = getDateFormat(pattern).parse(date);
            } catch (Exception e) {
            }
        }
        return myDate;
    }

    /**
     * 将日期转化为指定格式日期。失败返回null。
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return 日期
     * @throws RuntimeException 爬出运行时异常
     * @throws ParseException   转换异常
     */
    public static Date getDate(Date date, String pattern) {
        Date myDate = null;
        if (date != null) {
            String dateStr = getString(date, pattern);
            try {
                myDate = getDateFormat(pattern).parse(dateStr);
            } catch (ParseException | RuntimeException e) {
            }
        }
        return myDate;
    }

    /**
     * 将日期字符串转化为日期。失败返回null。
     *
     * @param date          日期字符串
     * @param dateStyleEnum 日期风格
     * @return 日期
     */
    public static Date getDate(String date, DateStyleEnum dateStyleEnum) {
        Date myDate = null;
        if (dateStyleEnum != null) {
            myDate = getDate(date, dateStyleEnum.getValue());
        }
        return myDate;
    }

    /**
     * 将日期转化为指定格式日期。失败返回null。
     *
     * @param date      日期
     * @param //pattern 日期格式
     * @return 日期
     * @throws RuntimeException 爬出运行时异常
     * @throws ParseException   转换异常
     */
    public static Date getDate(Date date, DateStyleEnum dateStyleEnum) {
        Date myDate = null;
        if (date != null) {
            myDate = getDate(date, dateStyleEnum.getValue());
        }
        return myDate;
    }

    /**
     * 返回当前年、月的最后一天的日期（字符串）
     *
     * @param year：当前年
     * @param month：当前月
     * @return
     */
    public static int getMonthDay(int year, int month) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.clear();
        rightNow.set(year, month - 1, 1);
        String thisDate = year + "-" + month + "-" + rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
        int days = rightNow.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }


    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return 日期字符串
     */
    public static String getString(Date date, String pattern) {
        String dateString = null;
        if (date != null) {
            try {
                dateString = getDateFormat(pattern).format(date);
            } catch (Exception e) {
            }
        }
        return dateString;
    }

    /**
     * 将日期转化为日期字符串。失败返回null。
     *
     * @param date          日期
     * @param dateStyleEnum 日期风格
     * @return 日期字符串
     */
    public static String getString(Date date, DateStyleEnum dateStyleEnum) {
        String dateString = null;
        if (dateStyleEnum != null) {
            dateString = getString(date, dateStyleEnum.getValue());
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date       旧日期字符串
     * @param newPattern 新日期格式
     * @return 新日期字符串
     */
    public static String getString(String date, String newPattern) {
        DateStyleEnum oldDateStyleEnum = getDateStyle(date);
        return getString(date, oldDateStyleEnum, newPattern);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date             旧日期字符串
     * @param newDateStyleEnum 新日期风格
     * @return 新日期字符串
     */
    public static String getString(String date, DateStyleEnum newDateStyleEnum) {
        DateStyleEnum oldDateStyleEnum = getDateStyle(date);
        return getString(date, oldDateStyleEnum, newDateStyleEnum);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date        旧日期字符串
     * @param olddPattern 旧日期格式
     * @param newPattern  新日期格式
     * @return 新日期字符串
     */
    public static String getString(String date, String olddPattern, String newPattern) {
        return getString(getDate(date, olddPattern), newPattern);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddDteStyle 旧日期风格
     * @param newParttern  新日期格式
     * @return 新日期字符串
     */
    public static String getString(String date, DateStyleEnum olddDteStyle, String newParttern) {
        String dateString = null;
        if (olddDteStyle != null) {
            dateString = getString(date, olddDteStyle.getValue(), newParttern);
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date             旧日期字符串
     * @param olddPattern      旧日期格式
     * @param newDateStyleEnum 新日期风格
     * @return 新日期字符串
     */
    public static String getString(String date, String olddPattern, DateStyleEnum newDateStyleEnum) {
        String dateString = null;
        if (newDateStyleEnum != null) {
            dateString = getString(date, olddPattern, newDateStyleEnum.getValue());
        }
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date             旧日期字符串
     * @param olddDteStyle     旧日期风格
     * @param newDateStyleEnum 新日期风格
     * @return 新日期字符串
     */
    public static String getString(String date, DateStyleEnum olddDteStyle, DateStyleEnum newDateStyleEnum) {
        String dateString = null;
        if (olddDteStyle != null && newDateStyleEnum != null) {
            dateString = getString(date, olddDteStyle.getValue(), newDateStyleEnum.getValue());
        }
        return dateString;
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return String 增加年份后的日期字符串
     */
    public static String addYear(String date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期
     */
    public static Date addYear(Date date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的月份。失败返回null
     *
     * @param date        日期
     * @param monthAmount 增加数量。可为负数
     * @return String 增加月份后的日期字符串
     */
    public static String addMonth(String date, int monthAmount) {
        return addInteger(date, Calendar.MONTH, monthAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date        日期
     * @param monthAmount 增加数量。可为负数
     * @return 增加月份后的日期
     */
    public static Date addMonth(Date date, int monthAmount) {
        return addInteger(date, Calendar.MONTH, monthAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期字符串
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期字符串
     */
    public static String addDay(String date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date addDay(Date date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date       日期字符串
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期字符串
     */
    public static String addHour(String date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date       日期
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期
     */
    public static Date addHour(Date date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date         日期字符串
     * @param minuteAmount 增加数量。可为负数
     * @return 增加分钟后的日期字符串
     */
    public static String addMinute(String date, int minuteAmount) {
        return addInteger(date, Calendar.MINUTE, minuteAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date        日期
     * @param //dayAmount 增加数量。可为负数
     * @return 增加分钟后的日期
     */
    public static Date addMinute(Date date, int minuteAmount) {
        return addInteger(date, Calendar.MINUTE, minuteAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date        日期字符串
     * @param //dayAmount 增加数量。可为负数
     * @return 增加秒钟后的日期字符串
     */
    public static String addSecond(String date, int secondAmount) {
        return addInteger(date, Calendar.SECOND, secondAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null
     *
     * @param date        日期
     * @param //dayAmount 增加数量。可为负数
     * @return 增加秒钟后的日期
     */
    public static Date addSecond(Date date, int secondAmount) {
        return addInteger(date, Calendar.SECOND, secondAmount);
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期字符串
     * @return 年份
     */
    public static int getYear(String date) {
        return getYear(getDate(date));
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        return getInteger(date, Calendar.YEAR);
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期字符串
     * @return 月份
     */
    public static int getMonth(String date) {
        date = getString(date, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
        return getMonth(getDate(date));
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        return getInteger(date, Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期字符串
     * @return 天
     */
    public static int getDay(String date) {
        return getDay(getDate(date));
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期
     * @return 天
     */
    public static int getDay(Date date) {
        return getInteger(date, Calendar.DATE);
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期字符串
     * @return 小时
     */
    public static int getHour(String date) {
        return getHour(getDate(date));
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期
     * @return 小时
     */
    public static int getHour(Date date) {
        return getInteger(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 分钟
     */
    public static int getMinute(String date) {
        return getMinute(getDate(date));
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期
     * @return 分钟
     */
    public static int getMinute(Date date) {
        return getInteger(date, Calendar.MINUTE);
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 秒钟
     */
    public static int getSecond(String date) {
        return getSecond(getDate(date));
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期
     * @return 秒钟
     */
    public static int getSecond(Date date) {
        return getInteger(date, Calendar.SECOND);
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format
     * @return
     */
    public static String timeStampToStringDate(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str 字符串日期
     * @param format   如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String stringDateToTimeStamp(String date_str, String format) {
        try {
            if (format == null || format.isEmpty()) {
                format = "yyyy-MM-dd HH:mm:ss";
            }

            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 时间
     */
    public static String getTime(String date) {
        return getString(date, DateStyleEnum.HH_MM_SS);
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期
     * @return 时间
     */
    public static String getTime(Date date) {
        return getString(date, DateStyleEnum.HH_MM_SS);
    }

    /**
     * 获取日期的星期。失败返回null。
     *
     * @param date 日期字符串
     * @return 星期
     */
    public static Week getWeek(String date) {
        Week week = null;
        DateStyleEnum dateStyleEnum = getDateStyle(date);
        if (dateStyleEnum != null) {
            Date myDate = getDate(date, dateStyleEnum);
            week = getWeek(myDate);
        }
        return week;
    }

    /**
     * 获取日期的星期。失败返回null。
     *
     * @param date 日期
     * @return 星期
     */
    public static Week getWeek(Date date) {
        Week week = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (weekNumber) {
            case 0:
                week = Week.SUNDAY;
                break;
            case 1:
                week = Week.MONDAY;
                break;
            case 2:
                week = Week.TUESDAY;
                break;
            case 3:
                week = Week.WEDNESDAY;
                break;
            case 4:
                week = Week.THURSDAY;
                break;
            case 5:
                week = Week.FRIDAY;
                break;
            case 6:
                week = Week.SATURDAY;
                break;
        }
        return week;
    }

    /**
     * 获取两个日期相差的秒数
     *
     * @param date      日期字符串
     * @param otherDate 另一个日期字符串
     * @return 相差秒数。如果失败则返回-1
     */
    public static Long getIntervalMinute(String date, String otherDate) {
        return getIntervalMinute(getDate(date), getDate(otherDate));
    }

    /**
     * 获取两个日期相差的秒数
     *
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 相差秒数。如果失败则返回-1
     */
    public static Long getIntervalMinute(Date date, Date otherDate) {
        Long time = -1L;
        Date dateTmp = getDate(DateUtils.getDate(date), DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
        Date otherDateTmp = getDate(DateUtils.getDate(otherDate), DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
        if (dateTmp != null && otherDateTmp != null) {
            time = Math.abs(dateTmp.getTime() - otherDateTmp.getTime());
        }
        return time;
    }

    /**
     * 获取两个日期相差的天数
     * 部分开始时间和结束时间
     *
     * @param date      日期字符串
     * @param otherDate 另一个日期字符串
     * @return 相差天数。如果失败则返回-1
     */
    public static int getIntervalDays(String date, String otherDate) {
        date = getString(date, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
        otherDate = getString(otherDate, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
        return getIntervalDays(getDate(date), getDate(otherDate));
    }

    public static Date stringToDate(String str) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            // Fri Feb 24 00:00:00 CST 2012
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // 2012-02-24
        date = java.sql.Date.valueOf(str);

        return date;
    }

    public static int daysBetween(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        if (Integer.parseInt(String.valueOf(between_days)) < 0) {
            return -Integer.parseInt(String.valueOf(between_days));
        } else {
            return Integer.parseInt(String.valueOf(between_days));
        }
    }

    /**
     * 获取两日期差，返回年月日差值
     *
     * @param date1
     * @param date2
     * @return
     */
    public static List<Integer> getDiffDates(String date1, String date2) {
        String dates = "";
        List<Integer> diffList = new ArrayList<Integer>();
        try {
            Date startDate = getDate2(date1);
            Date endDate = getDate2(date2);
            Calendar calS = Calendar.getInstance();
            // 开始时间
            calS.setTime(startDate);
            int startY = calS.get(Calendar.YEAR);
            int startM = calS.get(Calendar.MONTH);
            int startD = calS.get(Calendar.DATE);
            int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);
            // 结束时间
            calS.setTime(endDate);
            int endY = calS.get(Calendar.YEAR);
            int endM = calS.get(Calendar.MONTH);
            // 处理起止日期为同一天，默认服务为一天 示例：2016-01-01 至 2016-01-01
            int endD = calS.get(Calendar.DATE) + 1;//
            int endDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);
            int lday = endD - startD;
            // 每月按照30天计算
            if (endD < startD) {
                endM = endM - 1;
                lday = 30 - startD + endD;
            }
            /*
             * 按照正常日期计算 if (lday<0) { endM = endM -1; lday = startDayOfMonth+
             * lday; }
             */
            // 处理服务天数问题，示例：2016-01-01 到 2017-12-31 实际上是1年
            if (lday == endDayOfMonth) {
                endM = endM + 1;
                lday = 0;
            }
            int mos = (endY - startY) * 12 + (endM - startM);
            int lyear = mos / 12;
            int lmonth = mos % 12;
            diffList.add(lyear);
            diffList.add(lmonth);
            diffList.add(lday);
            dates = lyear + "年" + lmonth + "个月" + lday + "天";
            System.out.println(dates);
        } catch (Exception e) {
            e.printStackTrace();
            // System.out.println(e.getMessage());
        }
        return diffList;
    }

    public static List<String>[] getDateFigure(Date dStart, Date dEnd) {
        List<String>[] rtnLists = (List<String>[]) new List[3];
        List<String> ltYear = new ArrayList<String>();
        List<String> ltMonth = new ArrayList<String>();
        List<String> ltDay = new ArrayList<String>();
        rtnLists[0] = ltYear;
        rtnLists[1] = ltMonth;
        rtnLists[2] = ltDay;

        if (dStart.after(dEnd)) {
            return rtnLists;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar start = Calendar.getInstance();
        start.setTime(dStart);
        Calendar end = Calendar.getInstance();
        end.setTime(dEnd);
        end.add(Calendar.DAY_OF_MONTH, 1);
        String s = sdf.format(start.getTime());
        start.add(Calendar.YEAR, 1);

        while (end.compareTo(start) > -1) {
            start.add(Calendar.DAY_OF_MONTH, -1);
            ltYear.add(s + "-" + sdf.format(start.getTime()));
            start.add(Calendar.DAY_OF_MONTH, 1);
            s = sdf.format(start.getTime());
            start.add(Calendar.YEAR, 1);
        }
        start.add(Calendar.YEAR, -1);
        start.add(Calendar.MONTH, 1);
        while (end.compareTo(start) > -1) {
            start.add(Calendar.DAY_OF_MONTH, -1);
            ltMonth.add(s + "-" + sdf.format(start.getTime()));
            start.add(Calendar.DAY_OF_MONTH, 1);
            s = sdf.format(start.getTime());
            start.add(Calendar.MONTH, 1);
        }
        start.add(Calendar.MONTH, -1);
        while (end.after(start)) {
            start.add(Calendar.DAY_OF_MONTH, 1);
            ltDay.add(s);
            s = sdf.format(start.getTime());
        }
        return rtnLists;
    }

    public static Long dateDiff(String startTime, String endTime, String format) {
        long day = 0L;
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        try {
            // 获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            if (diff < 0) {
                diff = -diff;
            }
            day = diff / nd;// 计算差多少天
            long hour = diff % nd / nh;// 计算差多少小时
            long min = diff % nd % nh / nm;// 计算差多少分钟
            long sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟"
                    + sec + "秒。");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 判断结束时间，是否大于开始时间
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isEndTime(String startTime, String endTime) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date d1 = df.parse(startTime);
            Date d2 = df.parse(endTime);

            //判断毫秒值是否大，
            if (d2.getTime() > d1.getTime()) {
                return true;
            }

        } catch (Exception e) {
        }
        return false;
    }


    /**
     * 获取两个日期相差的天数
     *
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 相差天数。如果失败则返回-1
     */
    public static int getIntervalDays(Date date, Date otherDate) {
        int num = -1;
        Date dateTmp = getDate(DateUtils.getDate(date), DateStyleEnum.YYYY_MM_DD_DH);
        Date otherDateTmp = getDate(DateUtils.getDate(otherDate), DateStyleEnum.YYYY_MM_DD_DH);
        if (dateTmp != null && otherDateTmp != null) {
            long time = Math.abs(dateTmp.getTime() - otherDateTmp.getTime());
            num = (int) (time / (24 * 60 * 60 * 1000));
        }
        return num;
    }

    /**
     * 获取两个日期相差的月数
     *
     * @param date      日期字符串
     * @param otherDate 另一个日期字符串
     * @return 相差月数。如果失败则返回-1
     */
    public static int getIntervalMonths(String date, String otherDate) {
        return getIntervalMonths(getDate(date), getDate(otherDate));
    }

    /**
     * 获取两个日期相差的月数
     *
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 相差月数。如果失败则返回-1
     */
    public static int getIntervalMonths(Date date, Date otherDate) {
        int num = -1;
        int dateTmp = DateUtils.getMonth(date);
        int otherDateTmp = DateUtils.getMonth(otherDate);
        if (dateTmp > 0 && otherDateTmp > 0) {
            num = Math.abs(dateTmp - otherDateTmp);
            int intervalYears = DateUtils.getIntervalYears(date, otherDate);
            if (intervalYears > 0) {
                num += (intervalYears * 12);
            }
        }
        return num;
    }

    /**
     * 获取两个日期相差的年数
     *
     * @param date      日期字符串
     * @param otherDate 另一个日期字符串
     * @return 相差年数。如果失败则返回-1
     */
    public static int getIntervalYears(String date, String otherDate) {
        return getIntervalYears(getDate(date), getDate(otherDate));
    }

    /**
     * 获取两个日期相差的年数
     *
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 相差月数。如果失败则返回-1
     */
    public static int getIntervalYears(Date date, Date otherDate) {

        int num = -1;
        int dateTmp = DateUtils.getYear(date);
        int otherDateTmp = DateUtils.getYear(otherDate);
        if (dateTmp > 0 && otherDateTmp > 0) {
            num = Math.abs(dateTmp - otherDateTmp);
        }
        return num;
    }

    //public static boolean timeCompare(String beginDate, String endDate) {

    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param lastTime  the first date
     * @param afterTime the second date
     * @return true <br/>false
     */
    public static boolean timeCompare(String lastTime, String afterTime) {
        boolean isBigger = false;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt1 = null;
        Date dt2 = null;
        try {
            //格式化时间
            lastTime = getString(lastTime, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
            afterTime = getString(afterTime, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);

            dt1 = format.parse(lastTime);
            dt2 = format.parse(afterTime);

            if (dt1.getTime() > dt2.getTime()) {
                isBigger = true;
            } else if (dt1.getTime() < dt2.getTime()) {
                isBigger = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isBigger;
    }

    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param lastTime  the first date
     * @param afterTime the second date
     * @return true <br/>false
     */
    public static boolean timeCompare2(String lastTime, String afterTime) {
        boolean isBigger = false;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt1 = null;
        Date dt2 = null;
        try {
            //格式化时间
            lastTime = getString(lastTime, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
            afterTime = getString(afterTime, DateStyleEnum.YYYY_MM_DD_HH_MM_SS);

            dt1 = format.parse(lastTime);
            dt2 = format.parse(afterTime);

            if (dt1.getTime() >= dt2.getTime()) {
                isBigger = true;
            } else if (dt1.getTime() < dt2.getTime()) {
                isBigger = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return isBigger;
    }


    /**
     * 比较两日期大小
     *
     * @param date      日期字符串
     * @param otherDate 另一个日期字符串
     * @return 比较两日期大小。如果date>otherDate则返回true,否则false
     */
    public static Boolean whenGT(String date, String otherDate) {
        return whenGT(getDate(date), getDate(otherDate));
    }

    /**
     * 比较两日期大小
     *
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 比较两日期大小。如果date>otherDate则返回true,否则false
     */
    public static Boolean whenGT(Date date, Date otherDate) {
        Boolean flag = false;
        Date dateTmp = getDate(DateUtils.getDate(date), DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
        Date otherDateTmp = getDate(DateUtils.getDate(otherDate), DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
        if (dateTmp != null && otherDateTmp != null) {
            long time = dateTmp.getTime() - otherDateTmp.getTime();
            if (time > 0) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 比较两日期是否相等
     *
     * @param date      日期字符串
     * @param otherDate 另一个日期字符串
     * @return 比较两日期大小。如果date==otherDate则返回true,否则false
     */
    public static Boolean whenEQ(String date, String otherDate) {
        return whenEQ(getDate(date), getDate(otherDate));
    }

    /**
     * 比较两日期是否相等
     *
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 比较两日期大小。如果date==otherDate则返回true,否则false
     */
    public static Boolean whenEQ(Date date, Date otherDate) {
        Boolean flag = false;
        Date dateTmp = getDate(DateUtils.getDate(date), DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
        Date otherDateTmp = getDate(DateUtils.getDate(otherDate), DateStyleEnum.YYYY_MM_DD_HH_MM_SS);
        if (dateTmp != null && otherDateTmp != null) {
            long time = dateTmp.getTime() - otherDateTmp.getTime();
            if (time == 0) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 比较两日期是否在同一天
     *
     * @param date      日期字符串
     * @param otherDate 另一个日期字符串
     * @return 比较两日期大小。如果date==otherDate则返回true,否则false
     */
    public static Boolean whenEQDay(String date, String otherDate) {
        return whenEQ(getDate(date), getDate(otherDate));
    }

    /**
     * 比较两日期是否在同一天
     *
     * @param date      日期
     * @param otherDate 另一个日期
     * @return 比较两日期大小。如果date==otherDate则返回true,否则false
     */
    public static Boolean whenEQDay(Date date, Date otherDate) {
        Boolean flag = false;
        Date dateTmp = getDate(DateUtils.getDate(date), DateStyleEnum.YYYY_MM_DD);
        Date otherDateTmp = getDate(DateUtils.getDate(otherDate), DateStyleEnum.YYYY_MM_DD);
        if (dateTmp != null && otherDateTmp != null) {
            long time = dateTmp.getTime() - otherDateTmp.getTime();
            if (time == 0) {
                flag = true;
            }
        }
        return flag;
    }


    /**
     * 获取两个时间中间隔的年月日，按日历日期计算
     *
     * @param sYear
     * @param sMonth
     * @param sDay
     * @param eYear
     * @param eMonth
     * @param eDay
     * @return
     */
    public static String getIntervalCalendars(int sYear, int sMonth, int sDay, int eYear, int eMonth, int eDay) {

        int years = 0;  //两日期年数相减，所得
        int months = 0; //两日期月数相减，所得
        int days = 0;   //两日期天数相减，所得

        if (sYear != eYear) {
            years = eYear - sYear;
        }
        if (sMonth != eMonth) {
            months = eMonth - sMonth;
        }
        if (sDay != eDay + 1) {
            days = eDay - sDay + 1;
        }

        //如果两个日期，月数相减为负数，年数要减一
        if (months < 0) {
            years--;
            months += 12;
        }

        //如果两个日期，天相减为负数，月数要减一
        if (days < 0) {
            int lMonDays = 0;//上个月的总天数

            //如果月数为1，年数大于0，就减一年，月数为12，
            if (months == 1 && years > 0) {
                years--;
                months = 12;

                //如果月数不为1，年数不为0，就只减一个月
                //如果月数为1，年数为0，就也减一个月
            } else {
                months--;
            }

            //如果结束日期，月不等于1，就获取上个月的总天数
            if (eMonth != 1) {
                lMonDays = DateUtils.getMonthDay(eYear, eMonth - 1);  //返回当前年、月的最后一天的日期

                //如果结束日期，月等于1，就获取上一年最后一个月的总天数
            } else {
                lMonDays = DateUtils.getMonthDay(eYear - 1, 12);  //返回当前年、月的最后一天的日期
            }

            days = lMonDays + days;

            if (days < 0) {
                int lTwoMonDays = 0;//往前推第二个月的天数
                //如果月数为1，年数大于0，就减一年，月数为12，
                if (months == 1 && years > 0) {
                    years--;
                    months = 12;

                    //如果月数不为1，年数不为0，就只减一个月
                    //如果月数为1，年数为0，就也减一个月
                } else {
                    months--;
                }

                //如果结束日期，月不等于1，就获取前第二个月的总天数
                if (eMonth != 2) {
                    lTwoMonDays = DateUtils.getMonthDay(eYear, eMonth - 2);  //返回当前年、月的最后一天的日期

                    //如果结束日期，月等于1，就获取上一年最后一个月的总天数
                } else {
                    lTwoMonDays = DateUtils.getMonthDay(eYear - 2, 12);  //返回当前年、月的最后一天的日期
                }

                days = lTwoMonDays + days;
            }
        }

        String day = "";
        String dTime = "";
        if (years > 0 || months > 0) {
            if (days < 10 && days > 0) {
                day = "0" + days;
            } else {
                day = "" + days;
            }
        } else {
            day = "" + days;
        }

        if (years > 0) {
            if (days > 0) {
                dTime = years + "年" + months + "个月" + day + "天";
            } else if (months > 0) {
                dTime = years + "年" + months + "个月";
            } else {
                dTime = years + "年";
            }
        } else if (months > 0) {
            if (days > 0) {
                dTime = months + "个月" + day + "天";
            } else {
                dTime = months + "个月";
            }
        } else if (days > 0) {
            dTime = day + "天";
        }

        return dTime;
    }

    /**
     * 获取两个时间中间隔的月日(年累加到月数)，按日历日期计算
     *
     * @param sYear
     * @param sMonth
     * @param sDay
     * @param eYear
     * @param eMonth
     * @param eDay
     * @return
     */
    public static String getIntervalMonthAndDay(int sYear, int sMonth, int sDay, int eYear, int eMonth, int eDay) {

        int years = 0;  //两日期年数相减，所得
        int months = 0; //两日期月数相减，所得
        int days = 0;   //两日期天数相减，所得

        if (sYear != eYear) {
            years = eYear - sYear;
        }
        if (sMonth != eMonth) {
            months = eMonth - sMonth;
        }
        if (sDay != eDay + 1) {
            days = eDay - sDay + 1;
        }

        if (years > 0) {
            months = months + years * 12;
        }

        //如果两个日期，天相减为负数，月数要减一
        if (days < 0) {
            int lMonDays = 0;//上个月的总天数
            months--;

            //如果结束日期，月不等于1，就获取上个月的总天数
            if (eMonth != 1) {
                lMonDays = DateUtils.getMonthDay(eYear, eMonth - 1);  //返回当前年、月的最后一天的日期

                //如果结束日期，月等于1，就获取上一年最后一个月的总天数
            } else {
                lMonDays = DateUtils.getMonthDay(eYear - 1, 12);  //返回当前年、月的最后一天的日期
            }

            days = lMonDays + days;

            if (days < 0) {
                int lTwoMonDays = 0;//往前推第二个月的天数
                months--;

                //如果结束日期，月不等于1，就获取前第二个月的总天数
                if (eMonth != 2) {
                    lTwoMonDays = DateUtils.getMonthDay(eYear, eMonth - 2);  //返回当前年、月的最后一天的日期

                    //如果结束日期，月等于1，就获取上一年最后一个月的总天数
                } else {
                    lTwoMonDays = DateUtils.getMonthDay(eYear - 2, 12);  //返回当前年、月的最后一天的日期
                }

                days = lTwoMonDays + days;
            }
        }

        String day = "";
        String dTime = "";
        if (months > 0) {
            if (days < 10 && days > 0) {
                day = "0" + days;
            } else {
                day = "" + days;
            }
        } else {
            day = "" + days;
        }

        if (months > 0) {
            if (days > 0) {
                dTime = months + "个月" + day + "天";
            } else {
                dTime = months + "个月";
            }
        } else if (days > 0) {
            dTime = day + "天";
        }

        return dTime;
    }

    /**
     * 获取两个日期相差几个月
     *
     * @param start
     * @param end
     * @return
     * @author 石冬冬-Heil Hilter(dd.shi02@zuche.com)
     * @date 2016-11-30 下午7:57:32
     */
    public static int getMonth(Date start, Date end) {
        if (start.after(end)) {
            Date t = start;
            start = end;
            end = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(start);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(end);
        Calendar temp = Calendar.getInstance();
        temp.setTime(end);
        temp.add(Calendar.DATE, 1);
        //获得开始与结束相差多少年
        int year = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        //获得开始与结束相差多少月
        int month = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

        LogUtil.v("tag", "开始月的天数：" + startCalendar.get(Calendar.DATE));
        LogUtil.v("tag", "结束月的天数：" + endCalendar.get(Calendar.DATE));

        if ((startCalendar.get(Calendar.DATE) == 1) && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else {
            int data = temp.get(Calendar.DATE) - startCalendar.get(Calendar.DATE);
            LogUtil.v("tag", "相差的天数：" + data);
            if (data != 0) {
                return 0;
            }
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }


    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 通过毫秒值，手动计算日期间的相差的天数
     * <p>
     * 跨年不会出现问题
     * 使用此种方法的话需要注意
     * 如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0
     *
     * @throws ParseException
     */
    public static long daysOfTwo_2(String startTime, String endTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //跨年不会出现问题
        //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0
        Date fDate = sdf.parse(startTime);
        Date oDate = sdf.parse(endTime);
        long days = (oDate.getTime() - fDate.getTime()) / (1000 * 3600 * 24);
        return days;
    }


}
