package com.ferzerkerx.repository;

import java.util.List;

public interface BaseRepository<T> {

    T findById(Integer id);

    T insert(T type);

    T update(T type);

    void deleteByIds(Integer... ids);

    T delete(T type);

    List<T> findByCriteria(T typeCriteria);
}
