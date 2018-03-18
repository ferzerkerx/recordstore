package com.ferzerkerx.service;

import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;
import com.ferzerkerx.repository.ArtistRepository;
import com.ferzerkerx.repository.RecordRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class RecordStoreServiceImplTest {

    private RecordStoreService recordStoreService;
    private ArtistRepository artistRepository;
    private RecordRepository recordRepository;


    @BeforeEach
    void setUp() {
        artistRepository = mock(ArtistRepository.class);
        recordRepository = mock(RecordRepository.class);
        recordStoreService = new RecordStoreServiceImpl(artistRepository, recordRepository);
    }

    @AfterEach
    void tearDown() {
        verifyNoMoreInteractions(artistRepository);
        verifyNoMoreInteractions(recordRepository);
    }

    @Test
    void deleteRecordById() {
        int recordId = 1;
        recordStoreService.deleteRecordById(recordId);
        verify(recordRepository).deleteByIds(recordId);
    }

    @Test
    void deleteArtistWithRecordsById() {
        int artistId = 1;
        recordStoreService.deleteArtistWithRecordsById(artistId);
        verify(recordRepository).deleteByArtistId(artistId);
        verify(artistRepository).deleteByIds(artistId);
    }

    @Test
    void findAllArtists() {
        recordStoreService.findAllArtists();
        verify(artistRepository).findAllArtists();
    }

    @Test
    void findArtistById() {
        int artistId = 1;
        recordStoreService.findArtistById(artistId);
        verify(artistRepository).findById(artistId);
    }

    @Test
    void findMatchedArtistsByName() {
        String name = "someName";
        recordStoreService.findMatchedArtistsByName(name);
        verify(artistRepository).findMatchedArtistsByName(name);
    }

    @Test
    void findMatchedRecordByCriteria() {
        ArgumentCaptor<Record> albumArgumentCaptor = ArgumentCaptor.forClass(Record.class);

        String title = "someTitle";
        String year = "1995";
        recordStoreService.findMatchedRecordByCriteria(title, year);
        verify(recordRepository).findByCriteria(albumArgumentCaptor.capture());

        Record capturedRecord = albumArgumentCaptor.getValue();
        assertNotNull(capturedRecord);
        assertEquals(title, capturedRecord.getTitle());
        assertEquals(year, capturedRecord.getYear());
    }

    @Test
    void findRecordById() {
        int recordId = 1;
        recordStoreService.findRecordById(recordId);
        verify(recordRepository).findById(recordId);
    }

    @Test
    void findRecordsByArtist() {
        int artistId = 1;
        recordStoreService.findRecordsByArtist(artistId);
        verify(recordRepository).findByArtist(artistId);
    }

    @Test
    void saveArtist() {
        Artist artist = new Artist();
        recordStoreService.saveArtist(artist);
        verify(artistRepository).insert(artist);
    }

    @Test
    void saveRecord() {
        Record record = new Record();
        record.setTitle("someTitle");
        record.setYear("1995");
        int artistId = 1;

        ArgumentCaptor<Record> albumArgumentCaptor = ArgumentCaptor.forClass(Record.class);

        recordStoreService.saveRecord(artistId, record);
        verify(recordRepository).insert(albumArgumentCaptor.capture());

        Record capturedAlbum = albumArgumentCaptor.getValue();
        assertEquals(record, capturedAlbum);

        Artist capturedAlbumArtist = capturedAlbum.getArtist();
        assertNotNull(capturedAlbumArtist);
        assertEquals(artistId, capturedAlbumArtist.getId());
    }

    @Test
    void updateArtistById() {
        Artist artist = new Artist();
        recordStoreService.updateArtistById(artist);
        verify(artistRepository).update(artist);
    }

    @Test
    void updateRecordById() {
        Record record = new Record();
        recordStoreService.updateRecordById(record);
        verify(recordRepository).update(record);
    }
}