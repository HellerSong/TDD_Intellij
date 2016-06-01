package com.hstdd.dao;

import java.util.List;

/**
 * <p>Summary: Interface DAO for Data Access Object.</p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
interface IDao<T> {
	boolean add(T t);

	void deleteById(int id);

	boolean update(T t);

	boolean update(int id, String segmentName, String value);

	boolean update(int id, String segmentName, int value);

	T getById(int id);

	T getBySegmentValue(String segmentName, String segmentValue);

	List<T> getAll(int pageNumber, int pageSize, String sWhere);

	List<T> getAll(String sWhere);
}
