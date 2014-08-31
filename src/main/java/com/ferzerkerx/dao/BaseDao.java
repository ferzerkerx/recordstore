package com.ferzerkerx.dao;

import java.util.List;

public interface BaseDao<T> {

    T findById(Integer id);

    T insert(T type);

    T update(T type);

    void delete(int id);

    T delete(T type);

    List<T> findByCriteria(T typeCriteria);
}
