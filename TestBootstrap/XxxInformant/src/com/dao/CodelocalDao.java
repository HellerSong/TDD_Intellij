package com.dao;

import com.pojo.CodelocalPojo;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class CodelocalDao extends BaseDao<CodelocalPojo, Integer> {
    public CodelocalDao() {
        tableName = "codelocal";
        mainKeyName = null;
    }
}
