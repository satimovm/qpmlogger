package com.qpmLogger.utils;

import java.util.Date;

/**
 * User: Satimov Murad
 * Date: 8/9/17 3:43 PM
 */
public final class DataGetter {

    public static Long tryGetLong(Object value) {
        try {
            if (value == null) {
                return null;
            } else if (value instanceof Number) {
                return ((Number) value).longValue();
            } else if (value instanceof String) {
                return Long.valueOf((String) value);
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    public static Integer tryGetInt(Object value) {
        try {
            if (value == null) {
                return null;
            } else if (value instanceof Number) {
                return ((Number) value).intValue();
            } else if (value instanceof String) {
                return Integer.valueOf((String) value);
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    public static Double tryGetDouble(Object value) {
        try {
            if (value == null) {
                return null;
            } else if (value instanceof Number) {
                return ((Number) value).doubleValue();
            } else if (value instanceof String) {
                return Double.valueOf((String) value);
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    public static Boolean tryGetBoolean(Object value) {
        if (value == null) {
            return null;
        }
        return (Boolean) value;
    }

    public static String tryGetString(Object value) {
        if (value == null || !(value instanceof String)) {
            return null;
        }
        return (String) value;
    }

    public static Date tryGetDate(Object value) {
        if (value == null || !(value instanceof Date)) {
            return null;
        }
        return (Date) value;
    }
}
