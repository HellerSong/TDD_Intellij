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
public abstract class BaseViewDao<T, PK> {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Class<T> clazz;
    private DaoConverter daoConvert;
    public String mainTableName;
    public String mainKeyName;
    public String joinString;


    public BaseViewDao() {
        //// Get exactly what class T is
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        DevLog.write("BaseViewDao T: " + clazz.getName());

        //// Convert data cross database table and entity instance
        daoConvert = new DaoConverter(clazz, mainKeyName);
    }

    public int getTotalRecordCount(String sWhere) {
        if (sWhere == null)
            throw new InvalidParameterException();

        int count = 0;

        try {
            String sql = "select count(*) as count from " + mainTableName + " " + joinString + " " + sWhere;
            DevLog.write(sql);

            if (conn == null) {
                conn = MySqlUtil.getConnection();
            }
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (Exception e) {
            DevLog.write("Get total record count failed.");
        }

        return count;
    }

    public T getById(PK id) {
        if (id == null)
            throw new InvalidParameterException();

        Object obj = null;

        try {
            String sql = "select * from " + mainTableName + " " + joinString + " where " + mainKeyName + "=? ";
            DevLog.write(sql);

            if (conn == null) {
                conn = MySqlUtil.getConnection();
            }
            pstmt = conn.prepareStatement(sql);
            //// Set main key value via different PK type
            if (id.getClass().equals(int.class)) {
                pstmt.setInt(1, Integer.parseInt(id.toString()));
            } else {
                pstmt.setString(1, id.toString());
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                obj = daoConvert.convertDatabaseDataToEntityForGetting(clazz.newInstance(), rs);
            }
        } catch (Exception e) {
            DevLog.write("Get entity by id failed.");
        }

        return (T) obj;
    }

    public List<T> getAll(int pageNumber, int pageSize, String sWhere) {
        if (pageNumber <= 0 || pageSize <= 0 || sWhere == null)
            throw new InvalidParameterException();

        List<T> list = new ArrayList<T>();

        try {
            String sql = "select distinct * from " + mainTableName + " " + joinString + " " +
                    sWhere + " order by " + mainKeyName
                    + " desc limit " + pageSize * (pageNumber - 1) + "," + pageSize + ";";
            DevLog.write(sql);

            if (conn == null) {
                conn = MySqlUtil.getConnection();
            }
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                T t = daoConvert.convertDatabaseDataToEntityForGetting(clazz.newInstance(), rs);
                list.add(t);
            }
        } catch (Exception e) {
            DevLog.write("Get all entities for pagination failed.");
        }

        return list;
    }

    public List<T> getAll(String sWhere) {
        List<T> list = new ArrayList<T>();

        try {
            String sql = "select * from " + mainTableName + joinString + sWhere + " order by " + mainKeyName + " desc;";
            DevLog.write(sql);

            if (conn == null) {
                conn = MySqlUtil.getConnection();
            }
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                T t = daoConvert.convertDatabaseDataToEntityForGetting(clazz.newInstance(), rs);
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
