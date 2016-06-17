package com.dao;

import com.utils.DevLog;
import com.utils.MySqlUtil;

import java.lang.reflect.ParameterizedType;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Summary: IDao implementation for data exchanging between entity and db.</p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class BaseDao<T, PK> implements IDao<T, PK> {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Class<T> clazz;
    private DaoConverter daoConvert;
    private String tableName;
    private String mainKeyName;
    public int totalCount;


    public BaseDao() {
        //// Get exactly what class T is
        clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        DevLog.write("BaseDao T: " + clazz.getName());

        //// Mapping class to database table and main key
        tableName = DaoMapper.mapDbTableFromEntity(clazz);
        mainKeyName = DaoMapper.mapDbTableMainKeyFromEntity(clazz);

        //// Convert data cross database table and entity instance
        daoConvert = new DaoConverter(clazz, mainKeyName);

        //// Get table total record count
        totalCount = getTotalRecordCount();
    }

    private int getTotalRecordCount() {
        int count = 0;

        try {
            String sql = "select count(*) as count from " + tableName;
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

    public boolean add(T t) {
        if (t == null)
            throw new InvalidParameterException();

        try {
            // insert into t_admin(name, password) values(?, ?);
            // SQL command will be: INSERT INTO t_admin(adminId, name, password) VALUES(null, 'Heller', '123'');
            String sql = "insert into " + tableName + "(" +
                    daoConvert.getFieldNameJoinByComma() + ") values(" +
                    daoConvert.getFieldsHolderString() + ");";
            DevLog.write(sql);

            if (conn == null) {
                conn = MySqlUtil.getConnection();
            }
            pstmt = conn.prepareStatement(sql);
            return daoConvert.convertEntityToDatabaseDataForAdding(t, pstmt);
        } catch (Exception e) {
            DevLog.write("Add db data from entity failed.");
            return false;
        }
    }

    public void deleteById(int id) {
        // Often used an flag = -1 to track
    }

    public boolean update(T t) {
        if (t == null)
            throw new InvalidParameterException();

        try {
            String sql = "update " + tableName + " set " + daoConvert.getFieldNameJoinByEqualAndQuestion() + " where " + mainKeyName + "=?";
            DevLog.write(sql);

            if (conn == null) {
                conn = MySqlUtil.getConnection();
            }
            pstmt = conn.prepareStatement(sql);
            return daoConvert.convertEntityToDatabaseDataForUpdating(t, pstmt);
        } catch (Exception e) {
            DevLog.write("Update db data from entity failed.");
            return false;
        }
    }

    public <SegmentType> boolean update(PK id, String segmentName, SegmentType value) {
        if (id == null || segmentName == null || segmentName.length() <= 0 || value == null)
            throw new InvalidParameterException();

        try {
            String sql = "update " + tableName + " set " + segmentName + "=? where " + mainKeyName + "=?";
            DevLog.write(sql);

            if (conn == null) {
                conn = MySqlUtil.getConnection();
            }
            pstmt = conn.prepareStatement(sql);

            //// Set segment value via different segment type
            if (value.getClass().equals(int.class)) {
                pstmt.setInt(1, Integer.parseInt(value.toString()));
            } else if (value.getClass().equals(String.class)) {
                pstmt.setString(1, value.toString());
            } else if (value.getClass().equals(Timestamp.class)) {
                pstmt.setTimestamp(1, (Timestamp) value);
            } else if (value.getClass().equals(Boolean.class)) {
                pstmt.setBoolean(1, (Boolean) (value));
            }

            //// Set main key value via different PK type
            if (id.getClass().equals(int.class)) {
                pstmt.setInt(2, Integer.parseInt(id.toString()));
            } else {
                pstmt.setString(2, id.toString());
            }

            //// Do execution
            pstmt.execute();

            return true;
        } catch (Exception e) {
            DevLog.write("Update db data by segment failed.");

            return false;
        }
    }

    public T getById(PK id) {
        if (id == null)
            throw new InvalidParameterException();

        Object obj = null;

        try {
            String sql = "select * from " + tableName + " where " + mainKeyName + "=? ";
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

    private List<T> getAllRecords(String sql) throws Exception {
        List<T> list = new ArrayList<T>();

        if (conn == null) {
            conn = MySqlUtil.getConnection();
        }
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            @SuppressWarnings("unchecked")
            T t = daoConvert.convertDatabaseDataToEntityForGetting(clazz.newInstance(), rs);
            list.add(t);
        }

        return list;
    }

    public List<T> getAll(int pageNumber, int pageSize, String sWhere) {
        if (pageNumber <= 0 || pageSize <= 0 || sWhere == null)
            throw new InvalidParameterException();

        try {
            String sql = "select distinct * from " + tableName + " " + sWhere + " order by " + mainKeyName
                    + " desc limit " + pageSize * (pageNumber - 1) + "," + pageSize + ";";
            DevLog.write(sql);

            return getAllRecords(sql);
        } catch (Exception e) {
            DevLog.write("Get all entities for pagination failed.");
            return null;
        }
    }

    public List<T> getAll(String sWhere) {
        if (sWhere == null || sWhere.length() <= 0)
            throw new InvalidParameterException();

        try {
            String sql = "select * from " + tableName + " " + sWhere;
            DevLog.write(sql);

            return getAllRecords(sql);
        } catch (Exception e) {
            DevLog.write("Get all entities for where condition failed.");
            return null;
        }
    }

    public List<T> getAll() {
        try {
            String sql = "select distinct * from " + tableName;
            DevLog.write(sql);

            return getAllRecords(sql);
        } catch (Exception e) {
            DevLog.write("Get all entities failed.");
            return null;
        }
    }


    public void closeAll() {
        MySqlUtil.close(pstmt, rs, conn);
    }
}
