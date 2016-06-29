package com.utils;

import com.dao.DropdownDao;
import com.pojo.DropdownItem;

import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.List;

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

    public static String parseDropdownContent(String optionType, String optionValue) {
        if (optionType == null || optionType.length() <= 0 || optionValue == null)
            DevLog.write(new InvalidParameterException().toString());

        Hashtable<String, List<DropdownItem>> dropdownHt = DropdownDao.getRegionDropdownHt();
        List<DropdownItem> targetList = dropdownHt.get(optionType);

        if (optionValue.length() <= 0) {
            return targetList.get(0).getText();
        }

        for (DropdownItem p : targetList) {
            if (p.getId().equals(optionValue)) {
                return p.getText();
            }
        }

        return "error";
    }
}
