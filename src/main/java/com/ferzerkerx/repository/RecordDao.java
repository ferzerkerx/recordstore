package com.ferzerkerx.repository;

import java.util.List;
import com.ferzerkerx.model.Record;

public interface RecordDao extends BaseDao<Record> {

    void deleteByArtistId(int artistId);

    List<Record> findByArtist(int artistId);
}
