package com.ferzerkerx.repository.impl;

import com.ferzerkerx.repository.ArtistRepository;
import com.ferzerkerx.repository.RecordRepository;
import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.ferzerkerx.TestUtils.artist;
import static com.ferzerkerx.TestUtils.record;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.junit.jupiter.api.Assertions.*;


class RecordRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Test
    void testInsert() {
        Artist artist = artist();
        artistRepository.insert(artist);

        Record newRecord = record(artist);
        recordRepository.insert(newRecord);

        Record recordFromDb = recordRepository.findById(newRecord.getId());

        assertNotNull(recordFromDb);
        assertEquals(newRecord.getId(), recordFromDb.getId());
        assertEquals(newRecord.getTitle(), recordFromDb.getTitle());
        assertEquals(newRecord.getYear(), recordFromDb.getYear());
    }

    @Test
    void testUpdate() {
        Artist artist = artist();
        artistRepository.insert(artist);

        Record updatedRecord = record(artist);
        recordRepository.insert(updatedRecord);

        updatedRecord.setTitle("updated title");
        updatedRecord.setYear("2009");
        recordRepository.update(updatedRecord);

        Record dbRecord = recordRepository.findById(updatedRecord.getId());
        assertNotNull(dbRecord);
        assertEquals("updated title", dbRecord.getTitle());
        assertEquals("2009", dbRecord.getYear());
    }

    @Test
    void testDelete() {
        Artist artist = artist();
        artistRepository.insert(artist);

        Record record = record(artist);
        recordRepository.insert(record);

        Integer recordId = record.getId();
        recordRepository.delete(record);

        Record dbRecord = recordRepository.findById(recordId);
        assertNull(dbRecord);
    }

    @Test
    void testFindRecordsByArtist() {
        Artist artist = artist();
        artistRepository.insert(artist);

        Record record = record(artist);
        recordRepository.insert(record);

        List<Record> recordsByArtist = recordRepository.findByArtist(artist.getId());
        assertTrue(isNotEmpty(recordsByArtist));
        assertEquals(record.getId(), recordsByArtist.get(0).getId());
    }

    @Test
    void testFindByCriteria() {
        Artist artist = artist();
        artistRepository.insert(artist);

        Record record = record(artist);
        recordRepository.insert(record);

        List<Record> recordsByArtist = recordRepository.findByCriteria(record);
        assertTrue(isNotEmpty(recordsByArtist));
        assertEquals(record.getId(), recordsByArtist.get(0).getId());
    }

    @Test
    void testDeleteByArtist() {
        Artist artist = artist();
        artistRepository.insert(artist);

        Record record = record(artist);
        recordRepository.insert(record);

        List<Record> recordsByArtist = recordRepository.findByArtist(artist.getId());
        assertTrue(isNotEmpty(recordsByArtist));
        assertEquals(record.getId(), recordsByArtist.get(0).getId());

        recordRepository.deleteByArtistId(artist.getId());
        assertTrue(isEmpty(recordRepository.findByArtist(artist.getId())));
    }
}
