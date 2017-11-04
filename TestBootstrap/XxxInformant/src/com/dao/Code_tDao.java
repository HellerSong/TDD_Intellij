package com.dao;

import com.pojo.Code_tPojo;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class Code_tDao extends BaseDao<Code_tPojo, Integer> {
    public Code_tDao() {
        tableName = "code_t";
        mainKeyName = null;
    }
}
