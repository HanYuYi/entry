package com.HanYuYi.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class DateUtils {

    /***********************java8新的时间对象**************************/

    // 当前时区
    public static String currentZone = null;

    static {
        Calendar instance = Calendar.getInstance();
        currentZone = instance.getTimeZone().getID();
    }

    /**
     * 将日期时间字符串转化为时间对象
     * @param dateString
     * @return
     */
    public static LocalDateTime dateTimeStringToDate(String dateString) {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parseDate = LocalDateTime.parse(dateString, sdf);
        return parseDate;
    }

    /**
     * 将日期字符串转化为时间对象
     * @param dateString
     * @return
     */
    public static LocalDate dateStringToDate(String dateString) {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate parseDate = LocalDate.parse(dateString, sdf);
        return parseDate;
    }

    /**
     * 将时间字符串转化为时间对象
     * @param dateString
     * @return
     */
    public static LocalTime timeStringToDate(String dateString) {
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime parseDate = LocalTime.parse(dateString, sdf);
        return parseDate;
    }

    /**
     * 将时间对象转化为时间戳
     * @param date
     * @return
     */
    public static long dateToEpochMilli(LocalDateTime date) {
        long milli = date.toInstant(ZoneOffset.of(currentZone)).toEpochMilli();
        return milli;
    }

    /**
     * 将时间对象转化为时间戳，自定义时区
     * @param date
     * @param zoneId
     * @return
     */
    public static long dateToEpochMilli(LocalDateTime date, String zoneId) {
        String zone = zoneId != null ? zoneId : currentZone;
        long milli = date.toInstant(ZoneOffset.of(zone)).toEpochMilli();
        return milli;
    }
}
