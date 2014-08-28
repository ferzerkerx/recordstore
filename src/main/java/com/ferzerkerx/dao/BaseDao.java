package com.ferzerkerx.dao;

import java.util.List;

public interface BaseDao<T> {

    T insert(T type);

    T update(T type);

    T delete(T type);

    List<T> findByCriteria(T typeCriteria);

    T findById(Class<T> clazz, Integer id);
}
