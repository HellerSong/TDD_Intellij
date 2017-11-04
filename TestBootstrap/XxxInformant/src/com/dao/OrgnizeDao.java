package com.dao;

import com.pojo.OrgnizePojo;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class OrgnizeDao extends BaseDao<OrgnizePojo, String> {
    public OrgnizeDao() {
        tableName = "orgnize";
        mainKeyName = "ID";
        orderByName = "OwnerID,DisplayName";
        orderByType = "asc";

        totalCount = getTotalRecordCount();
    }
}
