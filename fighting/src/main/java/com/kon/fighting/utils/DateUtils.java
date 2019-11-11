package com.kon.fighting.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Description 时间工具类
 * @Author LK
 * @Date 2019/10/21 14:21
 * @Version V1.0
 **/
public class DateUtils {

    public static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat SDF_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 获取当前时间的YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        return SDF_DATE_FORMAT.format(new Date());
    }

    /**
     * 获取当前时间的YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getTime() {
        return SDF_TIME.format(new Date());
    }

    /**
     * 日期比较，如果s>=e 返回true 否则返回false
     *
     * @param s
     * @param e
     * @return
     */
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return s.compareTo(e) > 0;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取当前时间的后i天
     *
     * @param i
     * @return
     */
    public static String getAddDay(int i) {
        String currentTime = getTime();
        GregorianCalendar gCal = new GregorianCalendar(
                Integer.parseInt(currentTime.substring(0, 4)),
                Integer.parseInt(currentTime.substring(5, 7)) - 1,
                Integer.parseInt(currentTime.substring(8, 10)));
        gCal.add(GregorianCalendar.DATE, i);
        return SDF_DATE_FORMAT.format(gCal.getTime());
    }

    /**
     * 获取当前时间的后i天
     * 精确到秒
     *
     * @param i
     * @return
     */
    public static String getAddDayTime(int i) {
        Date date = new Date(System.currentTimeMillis() + i * 24 * 60 * 60 * 1000);
        return SDF_TIME.format(date);
    }

    /**
     * 获取当前时间的+多少秒
     * 精确到秒
     *
     * @param i
     * @return
     */
    public static String getAddDaySecond(int i) {
        Date date = new Date(System.currentTimeMillis() + i * 1000);
        return SDF_TIME.format(date);
    }

    /**
     * 是否过期
     *
     * @param expirationTime
     * @return
     */
    public static boolean isExpiration(String expirationTime) {
        // 当前时间
        String currentTime = getTime();
        if (compareDate(currentTime, expirationTime)) {
            // 当前时间比过期时间小，失效（true）
            return true;
        } else {
            // 当前时间比过期时间大，有效（false）
            return false;
        }
    }

}
