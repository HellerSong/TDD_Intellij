package com.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Administrator on 6/22/2016.
 */
public class DateUtil {
    public static String getCurrentTime(String formatPattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatPattern);
        Timestamp t = new Timestamp(System.currentTimeMillis());

        return formatter.format(t);
    }

}
