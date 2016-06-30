package com.dao;

import com.pojo.VClueListPojo;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class VClueListDao extends BaseViewDao<VClueListPojo, Integer> {
    public VClueListDao() {
        mainTableName = "jbkjxsly";
        mainKeyName = "jbkjxsly.JBKJXSLY_ID";
        joinString = "left join xscl on (jbkjxsly.JBKJXSLY_XH = xscl.JBKJXSLY_XH) ";

        totalCount = getTotalRecordCount();
    }
}
