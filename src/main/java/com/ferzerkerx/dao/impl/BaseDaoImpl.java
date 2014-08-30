package com.ferzerkerx.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import com.ferzerkerx.dao.BaseDao;


public class BaseDaoImpl<T> implements BaseDao<T> {

    private final Class<T> clazz;
    private EntityManager em = null;

    public BaseDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected EntityManager getEm() {
        return em;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    public T insert(T type) {
        em.persist(type);
        return type;
    }

    public T update(T type) {
        em.merge(type);
        return type;
    }

    public T delete(T type) {
        em.remove(type);
        return type;
    }

    public List<T> findByCriteria(T typeCriteria) {
        throw new UnsupportedOperationException("This is not implemented yet");
    }

    @Override
    public T findById(Integer id) {
        return (T) em.find(clazz, id);
    }

}
