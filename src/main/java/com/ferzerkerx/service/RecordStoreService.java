package com.ferzerkerx.service;

import java.util.Collection;
import java.util.List;
import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;

public interface RecordStoreService {
    void deleteRecordById(int recordId);

    void deleteArtistWithRecordsById(int artistId);

    Collection<Artist> findAllArtists();

    Artist findArtistById(int artistId);

    List<Artist> findMatchedArtistsByName(String name);

    List<Record> findMatchedRecordByCriteria(String title, String year);

    Record findRecordById(int recordId);

    List<Record> findRecordsByArtist(int artistId);

    void saveArtist(Artist artist);

    void saveRecord(int artistId, Record record);

    Artist updateArtistById(Artist artist);

    Record updateRecordById(Record record);
}
