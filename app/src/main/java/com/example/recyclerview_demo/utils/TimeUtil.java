package com.example.recyclerview_demo.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时间工具类
 * @author wyl
 * @date 2014-11-14
 */
public class TimeUtil {
    private static String TAG = "TimeUtil";

    /**
     * 按格式獲得時間
     * @param timeStr:
     * @param format:
     * @return : Millis seconds (Long)
     */
    public static long timeFormatToLong(String timeStr, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            return dateFormat.parse(timeStr).getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 時間(TimeInMillis) 轉換成 HH:mm:ss 格式
     * 若小時數為 0，則顯示 mm:ss
     * @param time: TimeInMillis
     * @return :
     */
    public static String longToHHmmss(Long time) {
        try {
            long hour = time/1000/60/60;
            String hourStr = hour == 0 ? "":hour+":";
            long min = time/1000/60%60;
            long sec = time/1000%60;
            return String.format("%s%02d:%02d", hourStr, min, sec);
        } catch (Exception e) {
            e.printStackTrace();
            return "--:--";
        }
    }

    public static String getTimeFromDateToString(Date addtime){
        String reTurnText = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        reTurnText = simpleDateFormat.format(addtime);
        return reTurnText;
    }

    /**
     * 按格式获得时间
     * @param timeStr
     * @param format
     * @return
     */
    public static String timeFormat(String timeStr, String format) {
        SimpleDateFormat sdf;
        if (format == "EEEE") {
            sdf = new SimpleDateFormat("EEEE", Locale.US);
        }else {
            sdf = new SimpleDateFormat(format);
        }
        try {
            Date time = new Date();
            if(ObjectUtils.isNotEmpty(timeStr)){
                time = sdf.parse(timeStr);
            }
            timeStr = sdf.format(time);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return timeStr;
    }


    /**
     * 時間(毫秒) 轉成 自訂格式字串
     * @param date: Millisecond
     * @param format: 自定義格式
     * @param timeZone: 時區
     * @return String
     */
    public static String timeFormat(Long date, String format, TimeZone timeZone) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            dateFormat.setTimeZone(timeZone);
            return dateFormat.format(new Date(date));

        } catch (Exception e) {
            Log.e(TAG, "解析日期失敗!!!");
            e.printStackTrace();
            return null;
        }
    }

    public static String timeFormat(Long date, String format) {
        return timeFormat(date, format, TimeZone.getDefault());
    }

    public static String timeFormat(Date date, String format) {
        return timeFormat(date.getTime(), format, TimeZone.getDefault());
    }


    /**
     * 字串轉換為Date
     * @param time:
     * @return :
     * @throws ParseException
     */
    public static Date convertStringToDate(String time) throws ParseException {
        // 假設input的字串是 "年-月-日 時:分:秒" 格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(time);
        return date;
    }

    //字串為Calendar
    public static Calendar convertStringToCalendar(String time) throws ParseException {
        // 假設input的字串是 "年-月-日 時:分:秒" 格式
        Date date = convertStringToDate(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static Calendar toCalendar(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }
}