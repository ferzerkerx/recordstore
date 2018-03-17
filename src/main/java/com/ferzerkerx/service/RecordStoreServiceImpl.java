package com.ferzerkerx.service;

import com.ferzerkerx.repository.ArtistRepository;
import com.ferzerkerx.repository.RecordDao;
import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RecordStoreServiceImpl implements RecordStoreService {

    private final ArtistRepository artistRepository;
    private final RecordDao recordDao;

    @Autowired
    public RecordStoreServiceImpl(ArtistRepository artistRepository, RecordDao recordDao) {
        this.artistRepository = artistRepository;
        this.recordDao = recordDao;
    }

    @Override
    public void deleteRecordById(int recordId) {
        recordDao.deleteByIds(recordId);
    }

    @Override
    public void deleteArtistWithRecordsById(int artistId) {
        recordDao.deleteByArtistId(artistId);
        artistRepository.deleteByIds(artistId);
    }

    @Override
    public List<Artist> findAllArtists() {
        return artistRepository.findAllArtists();
    }

    @Override
    public Artist findArtistById(int artistId) {
        return artistRepository.findById(artistId);
    }

    @Override
    public List<Artist> findMatchedArtistsByName(String name) {
        return artistRepository.findMatchedArtistsByName(name);
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
        return recordDao.findByArtist(artistId);
    }

    @Override
    public void saveArtist(Artist artist) {
        artistRepository.insert(artist);
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
        return artistRepository.update(artist);
    }

    @Override
    public Record updateRecordById(Record record) {
        return recordDao.update(record);
    }
}
