package com.ferzerkerx.repository.impl;

import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;
import com.ferzerkerx.repository.ArtistRepository;
import com.ferzerkerx.repository.RecordRepository;
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
        Record newRecord = insertRecord();

        Record recordFromDb = recordRepository.findById(newRecord.getId());

        assertNotNull(recordFromDb);
        assertEquals(newRecord.getId(), recordFromDb.getId());
        assertEquals(newRecord.getTitle(), recordFromDb.getTitle());
        assertEquals(newRecord.getYear(), recordFromDb.getYear());
    }

    @Test
    void testUpdate() {
        Record updatedRecord = insertRecord();

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
        Record record = insertRecord();

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
    void testFindByCriteria_withFullCriteria() {
        Record record = insertRecord();

        List<Record> recordsByArtist = recordRepository.findByCriteria(record);
        assertTrue(isNotEmpty(recordsByArtist));
        assertEquals(record.getId(), recordsByArtist.get(0).getId());
    }

    @Test
    void testFindByCriteria_withNoCriteria() {
        Record record = insertRecord();

        List<Record> recordsByArtist = recordRepository.findByCriteria(new Record());
        assertTrue(isNotEmpty(recordsByArtist));
        assertTrue(recordsByArtist.contains(record));
    }

    @Test
    void testDeleteByArtist() {
        Record record = insertRecord();
        int artistId = record.getArtist().getId();

        List<Record> recordsByArtist = recordRepository.findByArtist(artistId);
        assertTrue(isNotEmpty(recordsByArtist));
        assertEquals(record.getId(), recordsByArtist.get(0).getId());

        recordRepository.deleteByArtistId(artistId);
        assertTrue(isEmpty(recordRepository.findByArtist(artistId)));
    }

    private Record insertRecord() {
        Artist artist = artist();
        artistRepository.insert(artist);

        Record record = record(artist);
        recordRepository.insert(record);
        return record;
    }
}
