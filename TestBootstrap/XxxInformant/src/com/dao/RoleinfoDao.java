package com.dao;

import com.pojo.RoleinfoPojo;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class RoleinfoDao extends BaseDao<RoleinfoPojo, Integer> {


    public RoleinfoDao() {
        tableName = "roleinfo";
        mainKeyName = "Id";

        totalCount = getTotalRecordCount();
    }
}
