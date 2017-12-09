package com.ferzerkerx.dao.impl;

import com.ferzerkerx.dao.RecordDao;
import com.ferzerkerx.model.Record;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RecordDaoImpl extends BaseDaoImpl<Record> implements RecordDao {

    public RecordDaoImpl() {
        super(Record.class);
    }

    @Override
    public void deleteRecordsByArtistId(int artistId) {
        getEm().createQuery("DELETE FROM Record r WHERE r.artist.id = :id") //
                .setParameter("id", artistId) //
                .executeUpdate();
    }

    @Override
    public List<Record> findRecordsByArtist(int artistId) {
        TypedQuery<Record> query = createQuery("SELECT r FROM Record r WHERE r.artist.id = :id");
        query.setParameter("id", artistId);
        return query.getResultList();
    }


    @Override
    public List<Record> findByCriteria(Record typeCriteria) {
        CriteriaBuilder cb = getEm().getCriteriaBuilder();
        CriteriaQuery<Record> criteria = cb.createQuery(Record.class);
        Root<Record> c = criteria.from(Record.class);
        criteria.select(c);

        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(typeCriteria.getTitle())) {
            ParameterExpression<String> p = cb.parameter(String.class, "%" + typeCriteria.getTitle() + "%");
            predicates.add(cb.like(c.get("title"), p));
        }
        if (StringUtils.isNotEmpty(typeCriteria.getYear())) {
            ParameterExpression<String> p = cb.parameter(String.class, typeCriteria.getYear());
            predicates.add(cb.like(c.get("year"), p));
        }
        criteria.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        return getEm().createQuery(criteria).getResultList();
    }
}
