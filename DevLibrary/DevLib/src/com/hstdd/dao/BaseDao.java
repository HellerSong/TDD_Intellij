package com.hstdd.dao;

import com.hstdd.utils.DevLog;
import com.hstdd.utils.MySqlUtil;

import java.lang.reflect.ParameterizedType;
import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Summary: IDao implementation for data exchanging between entity and db.</p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
public class BaseDao<T> implements IDao<T> {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Class<?> myClass;
	private DaoConverter daoConvert;
	private String tableName;
	private String mainKeyName;
	public int totalCount;

	public BaseDao() {
		//// Get exactly what class T is
		ParameterizedType pType = (ParameterizedType) this.getClass().getGenericSuperclass();
		myClass = (Class<?>) pType.getActualTypeArguments()[0];
		DevLog.write("BaseDao T: " + myClass.getName());

		//// Mapping class to database table and main key
		tableName = DaoMapper.mapDbTableFromEntity(myClass);
		mainKeyName = DaoMapper.mapDbTableMainKeyFromEntity(myClass);

		//// Convert data cross database table and entity instance
		daoConvert = new DaoConverter(myClass, mainKeyName);

		//// Get table total record count
		totalCount = getTotalRecordCount();
	}

	public BaseDao(Class<T> entity) {
		//// Get exactly what class T is
		myClass = entity;
		DevLog.write("BaseDao T: " + myClass.getName());

		//// Mapping class to database table and main key
		tableName = DaoMapper.mapDbTableFromEntity(myClass);
		mainKeyName = DaoMapper.mapDbTableMainKeyFromEntity(myClass);

		//// Convert data cross database table and entity instance
		daoConvert = new DaoConverter(myClass, mainKeyName);

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

	@Override
	public boolean add(T t) {
		if (t == null)
			throw new InvalidParameterException();

		try {
			// insert into t_admin(name, password) values(?, ?);
			// SQL command will be: INSERT INTO t_admin(adminId, name, password) VALUES(null, 'Heller', '123'');
			String sql = "insert into " + tableName + "(" + daoConvert.getFieldNameJoinByComma() + ") values("
					+ daoConvert.getFieldsHolderString() + ");";
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

	@Override
	public void deleteById(int id) {
		// Often used an flag = -1 to track
	}

	@Override
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

	@Override
	public boolean update(int id, String segmentName, String value) {
		if (id <= 0 || segmentName == null || value == null)
			throw new InvalidParameterException();
		if (segmentName.length() <= 0)
			throw new InvalidParameterException();

		try {
			String sql = "update " + tableName + " set " + segmentName + "=? where " + mainKeyName + "=?";
			DevLog.write(sql);

			if (conn == null) {
				conn = MySqlUtil.getConnection();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);    // Set the String type segment value
			pstmt.setInt(2, id);
			pstmt.execute();
			return true;
		} catch (Exception e) {
			DevLog.write("Update db data by segment failed.");
			return false;
		}
	}

	@Override
	public boolean update(int id, String segmentName, int value) {
		if (id <= 0 || segmentName == null || segmentName.length() <= 0)
			throw new InvalidParameterException();

		try {
			String sql = "update " + tableName + " set " + segmentName + "=? where " + mainKeyName + "=?";
			DevLog.write(sql);

			if (conn == null) {
				conn = MySqlUtil.getConnection();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, value);    // Set the INT type segment value
			pstmt.setInt(2, id);
			pstmt.execute();
			return true;
		} catch (Exception e) {
			DevLog.write("Update db data by segment failed.");
			return false;
		}
	}

	@Override
	public T getById(int id) {
		if (id <= 0)
			throw new InvalidParameterException();

		T t = null;

		try {
			String sql = "select * from " + tableName + " where " + mainKeyName + "=? ";
			DevLog.write(sql);

			if (conn == null) {
				conn = MySqlUtil.getConnection();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				t = (T) daoConvert.convertDatabaseDataToEntityForGetting(myClass.newInstance(), rs);
			}
		} catch (Exception e) {
			DevLog.write("Get entity by id failed.");
		}

		return t;
	}

	@Override
	public T getBySegmentValue(String segmentName, String segmentValue) {
		if (segmentName == null || segmentName.length() == 0 || segmentValue == null) {
			return null;
		}

		T t = null;

		try {
			String sql = "select * from " + tableName + " where " + segmentName + "=?";
			DevLog.write(sql);

			if (conn == null) {
				conn = MySqlUtil.getConnection();
			}
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, segmentValue);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				t = (T) daoConvert.convertDatabaseDataToEntityForGetting(myClass.newInstance(), rs);
			}
		} catch (Exception e) {
			DevLog.write("Get entity by segment value failed.");
		}

		return t;
	}

	@Override
	public List<T> getAll(int pageNumber, int pageSize, String sWhere) {
		if (pageNumber <= 0 || pageSize <= 0 || sWhere == null)
			throw new InvalidParameterException();

		List<T> list = new ArrayList<>();

		try {
			String sql = "select distinct * from " + tableName + " " + sWhere + " order by " + mainKeyName
					+ " desc limit " + pageSize * (pageNumber - 1) + "," + pageSize + ";";
			DevLog.write(sql);

			if (conn == null) {
				conn = MySqlUtil.getConnection();
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				T t = (T) daoConvert.convertDatabaseDataToEntityForGetting(myClass.newInstance(), rs);
				list.add(t);
			}
		} catch (Exception e) {
			DevLog.write("Get all entity for pagination failed.");
		}

		return list;
	}

	@Override
	public List<T> getAll(String sWhere) {
		if (sWhere == null)
			throw new InvalidParameterException();

		List<T> list = new ArrayList<>();

		try {
			String sql = "select distinct * from " + tableName + " " + sWhere + " order by " + mainKeyName + " desc;";
			DevLog.write(sql);

			if (conn == null) {
				conn = MySqlUtil.getConnection();
			}
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				@SuppressWarnings("unchecked")
				T t = (T) daoConvert.convertDatabaseDataToEntityForGetting(myClass.newInstance(), rs);
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
