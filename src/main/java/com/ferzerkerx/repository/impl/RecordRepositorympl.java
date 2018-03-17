package com.ferzerkerx.repository.impl;

import com.ferzerkerx.repository.RecordDao;
import com.ferzerkerx.model.Record;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecordRepositorympl extends BaseRepositorympl<Record> implements RecordDao {

    public RecordRepositorympl() {
        super(Record.class);
    }

    @Override
    public void deleteByArtistId(int artistId) {
        getEm().createQuery("DELETE FROM Record r WHERE r.artist.id = :id") //
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
    public List<Record> findByCriteria(Record typeCriteria) {
        CriteriaBuilder criteriaBuilder = getEm().getCriteriaBuilder();
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

        return getEm()
                .createQuery(criteriaQuery)
                .getResultList();
    }
}
