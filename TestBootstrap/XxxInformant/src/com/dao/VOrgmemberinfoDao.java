package com.dao;

import com.pojo.VOrgmemberinfoPojo;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class VOrgmemberinfoDao extends BaseViewDao<VOrgmemberinfoPojo, Integer> {
    public VOrgmemberinfoDao() {
        mainTableName = "orgmemberinfo";
        mainKeyName = "orgmemberinfo.ID";
        joinString = "left join roleinfo on (orgmemberinfo.roleId = roleinfo.roleId) ";

        totalCount = getTotalRecordCount();
    }
}
