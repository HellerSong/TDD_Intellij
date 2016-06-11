package com.dao;

import com.utils.DevLog;
import com.utils.MySqlUtil;

import java.lang.reflect.ParameterizedType;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Summary: View model for joined entities and joined db tables.</p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public abstract class BaseViewDao<T> {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Class<?> myClass;
    private DaoConverter daoConvert;
    public String mainTableName;
    public String mainKeyName;
    public String joinString;

    public BaseViewDao() {
        //// Get exactly what class T is
        ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
        myClass = (Class<?>) pType.getActualTypeArguments()[0];
        DevLog.write("BaseDao T: " + myClass.getName());

        //// Convert data cross database table and entity instance
        daoConvert = new DaoConverter(myClass, mainKeyName);
    }

    public T getById(int id) {
        if (id <= 0)
            throw new InvalidParameterException();

        T t = null;

        try {
            String sql = "select * from " + mainTableName + " " + joinString + " where " + mainKeyName + "=? ";
            DevLog.write(sql);

            if (conn == null) {
                conn = MySqlUtil.getConnection();
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                t = daoConvert.convertDatabaseDataToEntityForGetting(myClass.newInstance(), rs);
            }
        } catch (Exception e) {
            DevLog.write("Get entity by id failed.");
        }

        return t;
    }

    public T getBySegmentValue(String segmentName, String segmentValue) {
        if (segmentName == null || segmentName.length() == 0 || segmentValue == null) {
            return null;
        }

        T t = null;

        try {
            String sql = "select * from " + mainTableName + joinString + " where " + segmentName + "=?";
            DevLog.write(sql);

            if (conn == null) {
                conn = MySqlUtil.getConnection();
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, segmentValue);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                t = daoConvert.convertDatabaseDataToEntityForGetting(myClass.newInstance(), rs);
            }
        } catch (Exception e) {
            DevLog.write("Get entity by segment value failed.");
        }

        return t;
    }

    public List<T> getAll(String sWhere) {
        List<T> list = new ArrayList<T>();

        try {
            String sql = "select * from " + mainTableName + joinString + sWhere + " order by " + mainKeyName;
            DevLog.write(sql);

            if (conn == null) {
                conn = MySqlUtil.getConnection();
            }
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                T t = daoConvert.convertDatabaseDataToEntityForGetting(myClass.newInstance(), rs);
                list.add(t);
            }
        } catch (Exception e) {
            DevLog.write(e);
        }

        return list;
    }

    public void closeAll() {
        MySqlUtil.close(pstmt, rs, conn);
    }
}
