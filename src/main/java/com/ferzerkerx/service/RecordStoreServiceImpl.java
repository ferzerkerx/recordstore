package com.ferzerkerx.service;

import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;
import com.ferzerkerx.repository.ArtistRepository;
import com.ferzerkerx.repository.RecordRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecordStoreServiceImpl implements RecordStoreService {

    @NonNull
    private final ArtistRepository artistRepository;
    @NonNull
    private final RecordRepository recordRepository;

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
    public List<Artist> findMatchedArtistsByName(@NonNull String name) {
        return artistRepository.findArtistsByName(name);
    }

    @Override
    public List<Record> findMatchedRecordByCriteria(@NonNull String title, @NonNull String year) {
        Record audioRecord = new Record();
        audioRecord.setTitle(title);
        audioRecord.setYear(year);
        return recordRepository.findByCriteria(audioRecord);
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
    public void saveArtist(@NonNull Artist artist) {
        artistRepository.insert(artist);
    }

    @Override
    public void saveRecord(int artistId, @NonNull Record audioRecord) {
        Artist artist = new Artist();
        artist.setId(artistId);

        audioRecord.setArtist(artist);
        recordRepository.insert(audioRecord);
    }

    @Override
    public Artist updateArtistById(@NonNull Artist artist) {
        return artistRepository.update(artist);
    }

    @Override
    public Record updateRecordById(@NonNull Record audioRecord) {
        return recordRepository.update(audioRecord);
    }
}
