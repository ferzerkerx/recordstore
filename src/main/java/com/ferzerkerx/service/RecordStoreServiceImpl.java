package com.ferzerkerx.service;

import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;
import com.ferzerkerx.repository.ArtistRepository;
import com.ferzerkerx.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
@Transactional
public class RecordStoreServiceImpl implements RecordStoreService {

    private final ArtistRepository artistRepository;
    private final RecordRepository recordRepository;

    @Autowired
    public RecordStoreServiceImpl(ArtistRepository artistRepository, RecordRepository recordRepository) {
        this.artistRepository = artistRepository;
        this.recordRepository = recordRepository;
    }

    @Override
    public void deleteRecordById(int recordId) {
        recordRepository.deleteByIds(recordId);
    }

    @Override
    public void deleteArtistWithRecordsById(int artistId) {
        recordRepository.deleteByArtistId(artistId);
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
        requireNonNull(name);
        return artistRepository.findMatchedArtistsByName(name);
    }

    @Override
    public List<Record> findMatchedRecordByCriteria(String title, String year) {
        requireNonNull(title);
        requireNonNull(year);

        Record record = new Record();
        record.setTitle(title);
        record.setYear(year);
        return recordRepository.findByCriteria(record);
    }

    @Override
    public Record findRecordById(int recordId) {
        return recordRepository.findById(recordId);
    }

    @Override
    public List<Record> findRecordsByArtist(int artistId) {
        return recordRepository.findByArtist(artistId);
    }

    @Override
    public void saveArtist(Artist artist) {
        requireNonNull(artist);
        artistRepository.insert(artist);
    }

    @Override
    public void saveRecord(int artistId, Record record) {
        requireNonNull(record);
        Artist artist = new Artist();
        artist.setId(artistId);

        record.setArtist(artist);
        recordRepository.insert(record);
    }

    @Override
    public Artist updateArtistById(Artist artist) {
        requireNonNull(artist);
        return artistRepository.update(artist);
    }

    @Override
    public Record updateRecordById(Record record) {
        requireNonNull(record);
        return recordRepository.update(record);
    }
}
