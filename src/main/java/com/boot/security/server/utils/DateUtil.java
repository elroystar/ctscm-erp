package com.boot.security.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间工具类
 *
 * @author xiaoleilu
 */
public class DateUtil {
    private static Logger log = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 毫秒
     */
    public final static long MS = 1;
    /**
     * 每秒钟的毫秒数
     */
    public final static long SECOND_MS = MS * 1000;
    /**
     * 每分钟的毫秒数
     */
    public final static long MINUTE_MS = SECOND_MS * 60;
    /**
     * 每小时的毫秒数
     */
    public final static long HOUR_MS = MINUTE_MS * 60;
    /**
     * 每天的毫秒数
     */
    public final static long DAY_MS = HOUR_MS * 24;

    /**
     * 标准日期格式
     */
    public final static String NORM_DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 标准时间格式
     */
    public final static String NORM_TIME_PATTERN = "HH:mm:ss";
    /**
     * 标准时间格式
     */
    public final static String NORM_DATE_TIME_PATTERN = "yyyyMMddHHmm";
    /**
     * 标准日期时间格式
     */
    public final static String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * HTTP头中日期时间格式
     */
    public final static String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";

    /**
     * 标准日期（不含时间）格式化器
     */
    private final static SimpleDateFormat NORM_DATE_FORMAT = new SimpleDateFormat(NORM_DATE_PATTERN);
    /**
     * 标准时间格式化器
     */
    private final static SimpleDateFormat NORM_TIME_FORMAT = new SimpleDateFormat(NORM_TIME_PATTERN);
    /**
     * 标准日期时间格式化器
     */
    private final static SimpleDateFormat NORM_DATETIME_FORMAT = new SimpleDateFormat(NORM_DATETIME_PATTERN);
    /**
     * HTTP日期时间格式化器
     */
    private final static SimpleDateFormat HTTP_DATETIME_FORMAT = new SimpleDateFormat(HTTP_DATETIME_PATTERN, Locale.US);

    /**
     * 当前时间，格式 yyyy-MM-dd HH:mm:ss
     *
     * @return 当前时间的标准形式字符串
     */
    public static String now() {
        return formatDateTime(new Date());
    }

    /**
     * 当前日期，格式 yyyy-MM-dd
     *
     * @return 当前日期的标准形式字符串
     */
    public static String today() {
        return formatDate(new Date());
    }

    // ------------------------------------ Format start ----------------------------------------------

    /**
     * 根据特定格式格式化日期
     *
     * @param date   被格式化的日期
     * @param format 格式
     * @return 格式化后的字符串
     */
    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date 被格式化的日期
     * @return 格式化后的日期
     */
    public static String formatDateTime(Date date) {
//		return format(d, "yyyy-MM-dd HH:mm:ss");
        return NORM_DATETIME_FORMAT.format(date);
    }

    /**
     * 格式化为Http的标准日期格式
     *
     * @param date 被格式化的日期
     * @return HTTP标准形式日期字符串
     */
    public static String formatHttpDate(Date date) {
//		return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US).format(date);
        return HTTP_DATETIME_FORMAT.format(date);
    }

    /**
     * 格式 yyyy-MM-dd
     *
     * @param date 被格式化的日期
     * @return 格式化后的字符串
     */
    public static String formatDate(Date date) {
//		return format(d, "yyyy-MM-dd");
        return NORM_DATE_FORMAT.format(date);
    }
    // ------------------------------------ Format end ----------------------------------------------

    // ------------------------------------ Parse start ----------------------------------------------

    /**
     * 将特定格式的日期转换为Date对象
     *
     * @param dateString 特定格式的日期
     * @param format     格式，例如yyyy-MM-dd
     * @return 日期对象
     */
    public static Date parse(String dateString, String format) {
        try {
            return (new SimpleDateFormat(format)).parse(dateString);
        } catch (ParseException e) {
            log.error("Parse " + dateString + " with format " + format + " error!", e);
        }
        return null;
    }

    /**
     * 格式yyyy-MM-dd HH:mm:ss
     *
     * @param dateString 标准形式的时间字符串
     * @return 日期对象
     */
    public static Date parseDateTime(String dateString) {
//		return parse(s, "yyyy-MM-dd HH:mm:ss");
        try {
            return NORM_DATETIME_FORMAT.parse(dateString);
        } catch (ParseException e) {
            log.error("Parse " + dateString + " with format " + NORM_DATETIME_FORMAT.toPattern() + " error!", e);
        }
        return null;
    }

    /**
     * 格式yyyy-MM-dd
     *
     * @param dateString 标准形式的日期字符串
     * @return 日期对象
     */
    public static Date parseDate(String dateString) {
        try {
            return NORM_DATE_FORMAT.parse(dateString);
        } catch (ParseException e) {
            log.error("Parse " + dateString + " with format " + NORM_DATE_PATTERN + " error!", e);
        }
        return null;
    }

    /**
     * 格式HH:mm:ss
     *
     * @param timeString 标准形式的日期字符串
     * @return 日期对象
     */
    public static Date parseTime(String timeString) {
        try {
            return NORM_TIME_FORMAT.parse(timeString);
        } catch (ParseException e) {
            log.error("Parse " + timeString + " with format " + NORM_TIME_PATTERN + " error!", e);
        }
        return null;
    }

    /**
     * 格式：<br>
     * 1、yyyy-MM-dd HH:mm:ss<br>
     * 2、yyyy-MM-dd<br>
     * 3、HH:mm:ss>
     *
     * @param dateStr 日期字符串
     * @return 日期
     */
    public static Date parse(String dateStr) {
        int length = dateStr.length();
        try {
            if (length == DateUtil.NORM_DATETIME_PATTERN.length()) {
                return parseDateTime(dateStr);
            } else if (length == DateUtil.NORM_DATE_PATTERN.length()) {
                return parseDate(dateStr);
            } else if (length == DateUtil.NORM_TIME_PATTERN.length()) {
                return parseTime(dateStr);
            }
        } catch (Exception e) {
            log.error("Parse " + dateStr + " with format normal error!", e);
        }
        return null;
    }
    // ------------------------------------ Parse end ----------------------------------------------

    // ------------------------------------ Offset start ----------------------------------------------

    /**
     * 昨天
     *
     * @return 昨天
     */
    public static Date yesterday() {
        return offsiteDate(new Date(), Calendar.DAY_OF_YEAR, -1);
    }

    /**
     * 上周
     *
     * @return 上周
     */
    public static Date lastWeek() {
        return offsiteDate(new Date(), Calendar.WEEK_OF_YEAR, -1);
    }

    /**
     * 上个月
     *
     * @return 上个月
     */
    public static Date lastMouth() {
        return offsiteDate(new Date(), Calendar.MONTH, -1);
    }

    /**
     * 获取指定日期偏移指定时间后的时间
     *
     * @param date          基准日期
     * @param calendarField 偏移的粒度大小（小时、天、月等）使用Calendar中的常数
     * @param offsite       偏移量，正数为向后偏移，负数为向前偏移
     * @return 偏移后的日期
     */
    public static Date offsiteDate(Date date, int calendarField, int offsite) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(calendarField, offsite);
        return cal.getTime();
    }
    // ------------------------------------ Offset end ----------------------------------------------

    /**
     * 判断两个日期相差的时长<br/>
     * 返回 minuend - subtrahend 的差
     *
     * @param subtrahend 减数日期
     * @param minuend    被减数日期
     * @param diffField  相差的选项：相差的天、小时
     * @return 日期差
     */
    public static long diff(Date subtrahend, Date minuend, long diffField) {
        long diff = minuend.getTime() - subtrahend.getTime();
        return diff / diffField;
    }

    /**
     * 计时，常用于记录某段代码的执行时间，单位：纳秒
     *
     * @param preTime 之前记录的时间
     * @return 时间差，纳秒
     */
    public static long spendNt(long preTime) {
        return System.nanoTime() - preTime;
    }

    /**
     * 计时，常用于记录某段代码的执行时间，单位：毫秒
     *
     * @param preTime 之前记录的时间
     * @return 时间差，毫秒
     */
    public static long spendMs(long preTime) {
        return System.currentTimeMillis() - preTime;
    }

    /**
     * add by litaoos2862
     * 获取 yyyyMMdd 格式的 integer 型日期
     *
     * @return
     */
    public static Integer getDateId() {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        String dateIdStr = sd.format(new Date(System.currentTimeMillis()));

        Integer result = Integer.parseInt(dateIdStr);

        return result;
    }

    public static String getDateIdBeforeTen(Integer before) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMddHHmm");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -10 * before);
        String dateIdStr = sd.format(calendar.getTime());
        return dateIdStr.substring(0, 11);
    }

    public static Integer tenMimuteDiff(String date1, String date2) {
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMddHHmm");
        Integer diff = 0;
        try {
            long d1 = formater.parse(date1).getTime();
            long d2 = formater.parse(date2).getTime();
            diff = Math.toIntExact((d1 - d2) / (MINUTE_MS * 10));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

    /**
     * add by litaoos2862
     * 获取指定格式的 integer 型日期
     *
     * @param formatStr
     * @return
     */
    public static String getDateId(String formatStr) {
        SimpleDateFormat sd = new SimpleDateFormat(formatStr);
        String dateIdStr = sd.format(new Date(System.currentTimeMillis()));
        return dateIdStr;
    }

    /**
     * add by litaoos2862
     * 获取指定格式的 integer 型日期的前一个小时
     *
     * @param formatStr
     * @return
     */
    public static String getDateIdPre(String formatStr) {
        SimpleDateFormat sd = new SimpleDateFormat(formatStr);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        String dateIdStr = sd.format(calendar.getTime());
        return dateIdStr;
    }

    /***
     * 获取指定格式的 integer 型日期的前一天
     * @param formatStr
     * @return
     */
    public static String getDateIdPreDay(String formatStr) {
        SimpleDateFormat sd = new SimpleDateFormat(formatStr);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, -1);
        String dateIdStr = sd.format(calendar.getTime());
        return dateIdStr;
    }

    /***
     * 获取指定格式的 integer 型日期的前一天
     * @param formatStr
     * @param day
     * @return
     */
    public static String getDateIdPreDay(String formatStr, String day) {
        String dateIdStr = null;
        try {
            SimpleDateFormat sd = new SimpleDateFormat(formatStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sd.parse(day));
            calendar.add(Calendar.DAY_OF_WEEK, -1);
            dateIdStr = sd.format(calendar.getTime());
            return dateIdStr;
        } catch (ParseException e) {
            log.error("获取指定格式的 integer 型日期的前一天：e:{}", e);
        }
        return dateIdStr;
    }

    /**
     * 获取指定格式的 integer 型日期的前一天
     *
     * @param formatStr
     * @param day
     * @param amount
     * @return
     */
    public static String getDateIdPreDay(String formatStr, String day, Integer amount) {
        String dateIdStr = null;
        try {
            SimpleDateFormat sd = new SimpleDateFormat(formatStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(sd.parse(day));
            calendar.add(Calendar.DAY_OF_WEEK, -amount);
            dateIdStr = sd.format(calendar.getTime());
            return dateIdStr;
        } catch (ParseException e) {
            log.error("获取指定格式的 integer 型日期的前一天：e:{}", e);
        }
        return dateIdStr;
    }

    /***
     * 获取指定格式的 integer 型日期的前一个月
     * @param formatStr
     * @return
     */
    public static String getDateIdPreMonth(String formatStr) {
        SimpleDateFormat sd = new SimpleDateFormat(formatStr);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        String dateIdStr = sd.format(calendar.getTime());
        return dateIdStr;
    }

    /**
     * add by litaoos2862
     * 获取 yyyyMMdd 格式的 integer 型明天日期
     *
     * @return
     */
    public static Integer getTomorrowId() {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, 1);
        String dateIdStr = sd.format(calendar.getTime());
        Integer result = Integer.parseInt(dateIdStr);

        return result;
    }

    /**
     * add by litaoos2862
     * 获取 yyyyMMdd 格式的 integer 型昨天日期
     *
     * @return
     */
    public static Integer getYesterdayId() {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, -1);
        String dateIdStr = sd.format(calendar.getTime());
        Integer result = Integer.parseInt(dateIdStr);

        return result;
    }

    /**
     * add by litaoos2862
     * 获取 yyyyMM 格式的 integer 型昨天日期（月份）
     *
     * @return
     */
    public static Integer getMonthStr() {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMM");
        String dateIdStr = sd.format(new Date(System.currentTimeMillis()));
        Integer result = Integer.parseInt(dateIdStr);
        return result;
    }

    /**
     * 获取 yyyyMMdd 格式的 日期
     *
     * @author hankun
     * @date 2018/8/2
     */
    public static String getSmsDateFormat() {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy年MM月dd日，HH:mm:ss");
        return sd.format(new Date(System.currentTimeMillis()));
    }

    public static boolean is201810MonthLater() throws ParseException {
        Date now = new Date();
        Date monthOf201810 = new SimpleDateFormat("yyyyMMdd").parse(("20181001"));
        return now.after(monthOf201810);
    }

    /**
     * 是否在一年内
     * 获取 yyyyMM 格式的 integer 型昨天日期（月份）
     *
     * @return
     */
    public static Boolean isInOneYear(String startdate) {
        Boolean result = true;
        Date startday = parse(startdate, "yyyyMMdd");
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startday);
        calendar.add(Calendar.YEAR, 1); //把日期往后增加一年，整数往后推，负数往前移
        Date endday = calendar.getTime();
        if (endday.before(today)) {
            result = false;
        }

        return result;
    }

    /**
     * 是否在一年后，7天前
     * 获取 yyyyMM 格式的 integer 型昨天日期（月份）
     *
     * @return
     */
    public static Boolean isAferYearBefore7days(String startdate) {
        Boolean result = true;
        Date startday = parse(startdate, "yyyyMMdd");
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startday);
        calendar.add(Calendar.YEAR, 1); //把日期往后增加一年，整数往后推，负数往前移
        calendar.add(Calendar.DAY_OF_YEAR, -7); //把日期往前增加7天
        Date endday = calendar.getTime();
        if (endday.before(today)) {
            result = false;
        }

        return result;
    }

    public static Date getBeginTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));

        return Date.from(zonedDateTime.toInstant());
    }

    public static Date getEndTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        return Date.from(zonedDateTime.toInstant());
    }

    public static Long getUnixTime(String time) {
        Long unixTime = null;
        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date d = sd.parse(time);
            unixTime = d.getTime() / 1000;
        } catch (ParseException e) {
            log.error("日期转换时间戳出错：e:{}", e);
        }
        return unixTime;
    }

}

