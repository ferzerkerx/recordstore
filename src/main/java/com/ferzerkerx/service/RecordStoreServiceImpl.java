package com.ferzerkerx.service;

import com.ferzerkerx.dao.ArtistDao;
import com.ferzerkerx.dao.RecordDao;
import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordStoreServiceImpl implements RecordStoreService {

    private final ArtistDao artistDao;
    private final RecordDao recordDao;

    @Autowired
    public RecordStoreServiceImpl(ArtistDao artistDao, RecordDao recordDao) {
        this.artistDao = artistDao;
        this.recordDao = recordDao;
    }

    @Override
    public void deleteRecordById(int recordId) {
        recordDao.deleteByIds(recordId);
    }

    @Override
    public void deleteArtistWithRecordsById(int artistId) {
        recordDao.deleteRecordsByArtistId(artistId);
        artistDao.deleteByIds(artistId);
    }

    @Override
    public List<Artist> findAllArtists() {
        return artistDao.findAllArtists();
    }

    @Override
    public Artist findArtistById(int artistId) {
        return artistDao.findById(artistId);
    }

    @Override
    public List<Artist> findMatchedArtistsByName(String name) {
        return artistDao.findMatchedArtistsByName(name);
    }

    @Override
    public List<Record> findMatchedRecordByCriteria(String title, String year) {
        Record record = new Record();
        record.setTitle(title);
        record.setYear(year);
        return recordDao.findByCriteria(record);
    }

    @Override
    public Record findRecordById(int recordId) {
        return recordDao.findById(recordId);
    }

    @Override
    public List<Record> findRecordsByArtist(int artistId) {
        return recordDao.findRecordsByArtist(artistId);
    }

    @Override
    public void saveArtist(Artist artist) {
        artistDao.insert(artist);
    }

    @Override
    public void saveRecord(int artistId, Record record) {
        Artist artist = new Artist();
        artist.setId(artistId);

        record.setArtist(artist);
        recordDao.insert(record);
    }

    @Override
    public Artist updateArtistById(Artist artist) {
        return artistDao.update(artist);
    }

    @Override
    public Record updateRecordById(Record record) {
        return recordDao.update(record);
    }
}
