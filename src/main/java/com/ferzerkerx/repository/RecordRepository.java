package com.ferzerkerx.repository;

import com.ferzerkerx.model.Record;

import java.util.List;

public interface RecordRepository extends BaseRepository<Record> {

    void deleteByArtistId(int artistId);

    List<Record> findByArtist(int artistId);
}
