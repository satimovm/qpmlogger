package com.qpmLogger.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Satimov Murad
 * Date: 8/14/17 5:01 PM
 */
public class DateUtils {

    public static String formatLong(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(date);
    }
}
