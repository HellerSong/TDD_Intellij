package com.dao;

import com.pojo.XsclPojo;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class XsclDao extends BaseDao<XsclPojo, String> {
    public XsclDao() {
        tableName = "xscl";
        mainKeyName = "JBKJXSLY_XH";

        totalCount = getTotalRecordCount();
    }
}
