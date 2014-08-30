package com.ferzerkerx.service;

import java.util.Collection;
import java.util.List;
import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;

public interface RecordStoreService {
    Collection<Artist> findAllArtists();

    Artist findArtistById(int artistId);

    Record findRecordById(int recordId);

    List<Record> findRecordsByArtist(int artistId);

    void saveArtist(Artist artist);

    void saveRecord(int artistId, Record record);
}
