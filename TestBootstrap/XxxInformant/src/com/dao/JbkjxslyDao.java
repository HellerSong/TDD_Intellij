package com.dao;

import com.pojo.JbkjxslyPojo;
import com.utils.DevLog;
import com.utils.MySqlUtil;

/**
 * <p>Summary : </p>
 * <p>Authors : Heller Song (HellerSong@Outlook.com)</p>
 */
public class JbkjxslyDao extends BaseDao<JbkjxslyPojo, Integer> {

    public int getLastAddedId() {
        int id = 0;

        try {
            String sql = "select max(" + mainKeyName + ") as maxId from " + tableName;

            if (conn == null) {
                conn = MySqlUtil.getConnection();
            }
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                id = rs.getInt("maxId");
            }
        } catch (Exception e) {
            DevLog.write("Get total record count failed.");
        }

        return id;
    }
}
