package com.ferzerkerx.repository.impl;

import com.ferzerkerx.model.Record;
import com.ferzerkerx.repository.RecordRepository;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecordRepositoryImpl extends BaseRepositorympl<Record> implements RecordRepository {

    public RecordRepositoryImpl(EntityManager entityManager) {
        super(Record.class, entityManager);
    }

    @Override
    public void deleteByArtistId(int artistId) {
        getEntityManager().createQuery("DELETE FROM Record r WHERE r.artist.id = :id") //
                .setParameter("id", artistId) //
                .executeUpdate();
    }

    @Override
    public List<Record> findByArtist(int artistId) {
        TypedQuery<Record> query = createQuery("SELECT r FROM Record r WHERE r.artist.id = :id");
        query.setParameter("id", artistId);
        return query.getResultList();
    }


    @Override
    public List<Record> findByCriteria(@NonNull Record typeCriteria) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Record> criteriaQuery = criteriaBuilder.createQuery(Record.class);
        Root<Record> root = criteriaQuery.from(Record.class);
        criteriaQuery.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(typeCriteria.getTitle())) {
            predicates.add(criteriaBuilder.like(root.get("title"), "%" + typeCriteria.getTitle() + "%"));
        }
        if (StringUtils.isNotEmpty(typeCriteria.getYear())) {
            predicates.add(criteriaBuilder.like(root.get("year"), typeCriteria.getYear()));
        }
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));

        return getEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();
    }
}
