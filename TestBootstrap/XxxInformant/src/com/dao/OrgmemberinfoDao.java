package com.dao;

import com.pojo.OrgmemberinfoPojo;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class OrgmemberinfoDao extends BaseDao<OrgmemberinfoPojo, Integer> {
    public OrgmemberinfoDao() {
        tableName = "orgmemberinfo";
        mainKeyName = "ID";

        totalCount = getTotalRecordCount();
    }
}
