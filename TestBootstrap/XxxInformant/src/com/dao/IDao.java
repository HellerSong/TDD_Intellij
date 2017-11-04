package com.dao;

import java.util.List;

/**
 * <p>Summary: Interface DAO for Data Access Object.</p>
 * <p>Authors: Heller Song (HellerSong@Outlook.com)</p>
 **/
interface IDao<T, PK> {
    boolean add(T t);

    void deleteById(int id);

    boolean update(T t);

    <SegmentType> boolean update(PK id, String segmentName, SegmentType value);

    T getById(PK id);

    List<T> getAll(int pageNumber, int pageSize, String sWhere);

    List<T> getAll(String sWhere);

    List<T> getAll();
}
