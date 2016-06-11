package com.utils;

import java.sql.Timestamp;

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class Parser {
    public static int parseInt(String numberStr) {
        if (numberStr == null) {
            return -1;
        } else if (numberStr.length() == 0) {
            return 0;
        } else {
            return Integer.parseInt(numberStr);
        }
    }

    public static Timestamp parseDate(String dateStr) {
        if (dateStr == null || dateStr.length() == 0) {
            return new Timestamp(System.currentTimeMillis());
        } else {
            if (dateStr.length() <= 10)
                dateStr += " 00:00:000.000";

            return Timestamp.valueOf(dateStr);
        }
    }

    public static boolean parseSwitch(String switchStatusStr) {
        if (switchStatusStr == null || switchStatusStr.length() == 0) {
            return false;
        } else if (switchStatusStr.equalsIgnoreCase("on")) {
            return true;
        }

        return true;
    }
}
