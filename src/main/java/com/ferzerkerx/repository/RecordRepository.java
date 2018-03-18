package com.ferzerkerx.repository;

import java.util.List;
import com.ferzerkerx.model.Record;

public interface RecordRepository extends BaseDao<Record> {

    void deleteByArtistId(int artistId);

    List<Record> findByArtist(int artistId);
}
