package com.ferzerkerx.dao.impl;

import javax.persistence.TypedQuery;
import java.util.List;
import com.ferzerkerx.dao.RecordDao;
import com.ferzerkerx.model.Record;
import org.springframework.stereotype.Repository;

@Repository
public class RecordDaoImpl extends BaseDaoImpl<Record> implements RecordDao {
    public RecordDaoImpl() {
        super(Record.class);
    }

    @Override
    public List<Record> findRecordsByArtist(int artistId) {
        TypedQuery<Record> query = getEm().createQuery("SELECT r FROM Record r WHERE r.artist.id = :id", Record.class);
        query.setParameter("id", artistId);
        return query.getResultList();
    }
}
