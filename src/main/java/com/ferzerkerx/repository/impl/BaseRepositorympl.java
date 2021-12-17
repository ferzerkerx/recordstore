package com.ferzerkerx.repository.impl;

import com.ferzerkerx.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

@Repository
public abstract class BaseRepositorympl<T> implements BaseRepository<T> {

    private final Class<T> clazz;
    private final EntityManager entityManager;

    protected BaseRepositorympl(Class<T> clazz, EntityManager em) {
        this.clazz = clazz;
        this.entityManager = em;
    }

    EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public T insert(T type) {
        entityManager.persist(type);
        return type;
    }

    @Override
    public T update(T type) {
        entityManager.merge(type);
        return type;
    }

    @Override
    public void deleteByIds(Integer... ids) {
        entityManager.createQuery(String.format("DELETE FROM %s e WHERE e.id in :ids", clazz.getName())) //
                .setParameter("ids", Arrays.asList(ids)).executeUpdate();
    }

    @Override
    public T delete(T type) {
        T managedObject = entityManager.merge(type);
        entityManager.remove(managedObject);
        return managedObject;
    }

    @Override
    public List<T> findByCriteria(T typeCriteria) {
        throw new UnsupportedOperationException("This is not implemented yet");
    }

    @Override
    public T findById(Integer id) {
        return entityManager.find(clazz, id);
    }

    TypedQuery<T> createQuery(String query) {
        return entityManager.createQuery(query, clazz);
    }

}
