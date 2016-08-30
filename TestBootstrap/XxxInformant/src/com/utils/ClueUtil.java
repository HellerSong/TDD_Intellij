package com.utils;

/**
 * <p>Summary: </p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class ClueUtil {

    public static String getClueTypeWhereString(int isALLClue, int isCFClue, int isXSClue, int isJCGJClue, int isTJJClue, int isSBJClue) {
        String sWhere = "";

        if (isALLClue == 1) {

        } else if (isXSClue == 1) {
            sWhere += " and JBKJXSLY_JBJCGJWFWJ <> '1'";
            // 因为有多个被举报人，所以用like
            sWhere += " and (JBKJXSLY_ZJDM not like '%05%' or JBKJXSLY_ZJDM not like '%06%')";  // 正厅级、副厅级
            sWhere += " and (JBKJXSLY_ZJDM not like '%03%' or JBKJXSLY_ZJDM not like '%04%')";  // 正部级、副部级
        } else if (isCFClue == 1) {

        } else if (isJCGJClue == 1) {
            sWhere += " and JBKJXSLY_JBJCGJWFWJ = '1'";
            sWhere += " and (JBKJXSLY_ZJDM not like '%05%' or JBKJXSLY_ZJDM not like '%06%')";  // 正厅级、副厅级
            sWhere += " and (JBKJXSLY_ZJDM not like '%03%' or JBKJXSLY_ZJDM not like '%04%')";  // 正部级、副部级
        } else if (isTJJClue == 1) {
            sWhere += " and (JBKJXSLY_ZJDM like '%05%' or JBKJXSLY_ZJDM like '%06%')";  // 正厅级、副厅级
        } else if (isSBJClue == 1) {
            sWhere += " and (JBKJXSLY_ZJDM like '%03%' or JBKJXSLY_ZJDM like '%04%')";  // 正部级、副部级
        }

        return sWhere;
    }
}
