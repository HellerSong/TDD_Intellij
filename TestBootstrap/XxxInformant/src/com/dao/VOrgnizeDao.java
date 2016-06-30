package com.dao;

import com.pojo.OrgnizePojo;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class VOrgnizeDao extends BaseViewDao<OrgnizePojo, Integer> {
    public VOrgnizeDao() {
        mainTableName = "orgnize as a";
        mainKeyName = "ID";
        joinString = "left join orgnize as b on (b.OwnerID = a.ID) ";

        totalCount = getTotalRecordCount();
    }
}
