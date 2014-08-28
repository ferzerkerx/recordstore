package com.ferzerkerx.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import com.ferzerkerx.dao.BaseDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BaseDaoImpl<T> implements BaseDao<T> {


    private EntityManager em = null;

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
    public T findById(Class<T> clazz, Integer id) {
        return (T) em.find(clazz, id);
    }

}
