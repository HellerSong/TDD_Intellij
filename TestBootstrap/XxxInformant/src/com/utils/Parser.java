package com.utils;

import com.dao.DropdownDao;
import com.pojo.DropdownItem;
import com.pojo.DropdownTreeNode;

import java.security.InvalidParameterException;
import java.sql.Timestamp;
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

        List<DropdownItem> targetList = DropdownDao.getRegionDropdownHt().get(optionType);

        if (optionValue.length() <= 0 || optionValue.equals("0")) {
            return "";
        }

        for (DropdownItem d : targetList) {
            if (d.getId().equals(optionValue)) {
                return d.getText();
            }
        }

        return "Error";
    }

    public static String parseTreeDropdownContent(String optionType, String optionValue) {
        if (optionType == null || optionType.length() <= 0 || optionValue == null)
            DevLog.write(new InvalidParameterException().toString());

        List<DropdownTreeNode> targetList = DropdownDao.getTreeDropdownHt().get(optionType);

        if (optionValue.length() <= 0 || optionValue.equals("0")) {
            return "";
        }

        for (DropdownTreeNode node : targetList) {
            if (node.getId().equals(optionValue)) {
                return node.getText();
            }
        }

        for (DropdownTreeNode node : targetList) {
            List<DropdownItem> childItemList = node.getChildren();
            if (childItemList != null) {
                for (DropdownItem d : childItemList) {
                    if (d.getId().equals(optionValue)) {
                        return d.getText();
                    }
                }
            }
        }

        return "Error";
    }
}
