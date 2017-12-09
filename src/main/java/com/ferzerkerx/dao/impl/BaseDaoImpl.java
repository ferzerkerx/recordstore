package com.ferzerkerx.dao.impl;

import com.ferzerkerx.dao.BaseDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Transactional
@Repository
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

    private final Class<T> clazz;

    @PersistenceContext
    private EntityManager em;

    protected BaseDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Override
    public T insert(T type) {
        em.persist(type);
        return type;
    }

    @Override
    public T update(T type) {
        em.merge(type);
        return type;
    }

    @Override
    public void deleteByIds(Integer... ids) {
        em.createQuery(String.format("DELETE FROM %s e WHERE e.id in :ids", clazz.getName())) //
                .setParameter("ids", Arrays.asList(ids)).executeUpdate();
    }

    @Override
    public T delete(T type) {
        T managedObject = em.merge(type);
        em.remove(managedObject);
        return managedObject;
    }

    @Override
    public List<T> findByCriteria(T typeCriteria) {
        throw new UnsupportedOperationException("This is not implemented yet");
    }

    @Override
    public T findById(Integer id) {
        return em.find(clazz, id);
    }

    TypedQuery<T> createQuery(String query) {
        return em.createQuery(query, clazz);
    }

}
