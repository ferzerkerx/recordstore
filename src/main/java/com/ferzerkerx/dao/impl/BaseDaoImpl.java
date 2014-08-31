package com.ferzerkerx.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import com.ferzerkerx.dao.BaseDao;

//TODO Fer extends SimpleJpaRepository?
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
    public void delete(int id) {
        em.createQuery(String.format("DELETE FROM %s e WHERE e.id = :id", clazz.getName())) //
        .setParameter("id", id).executeUpdate();
    }

    @Override
    public T delete(T type) {
        em.remove(type);
        return type;
    }



    @Override
    public List<T> findByCriteria(T typeCriteria) {
        throw new UnsupportedOperationException("This is not implemented yet");
    }

    @Override
    public T findById(Integer id) {
        return (T) em.find(clazz, id);
    }

}
