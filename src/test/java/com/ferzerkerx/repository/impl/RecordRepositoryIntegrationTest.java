package com.ferzerkerx.repository.impl;

import com.ferzerkerx.repository.ArtistRepository;
import com.ferzerkerx.repository.RecordDao;
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
    private RecordDao recordDao;

    @Test
    void testInsert() {
        Artist artist = artist();
        artistRepository.insert(artist);

        Record newRecord = record(artist);
        recordDao.insert(newRecord);

        Record recordFromDb = recordDao.findById(newRecord.getId());

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
        recordDao.insert(updatedRecord);

        updatedRecord.setTitle("updated title");
        updatedRecord.setYear("2009");
        recordDao.update(updatedRecord);

        Record dbRecord = recordDao.findById(updatedRecord.getId());
        assertNotNull(dbRecord);
        assertEquals("updated title", dbRecord.getTitle());
        assertEquals("2009", dbRecord.getYear());
    }

    @Test
    void testDelete() {
        Artist artist = artist();
        artistRepository.insert(artist);

        Record record = record(artist);
        recordDao.insert(record);

        Integer recordId = record.getId();
        recordDao.delete(record);

        Record dbRecord = recordDao.findById(recordId);
        assertNull(dbRecord);
    }

    @Test
    void testFindRecordsByArtist() {
        Artist artist = artist();
        artistRepository.insert(artist);

        Record record = record(artist);
        recordDao.insert(record);

        List<Record> recordsByArtist = recordDao.findByArtist(artist.getId());
        assertTrue(isNotEmpty(recordsByArtist));
        assertEquals(record.getId(), recordsByArtist.get(0).getId());
    }

    @Test
    void testFindByCriteria() {
        Artist artist = artist();
        artistRepository.insert(artist);

        Record record = record(artist);
        recordDao.insert(record);

        List<Record> recordsByArtist = recordDao.findByCriteria(record);
        assertTrue(isNotEmpty(recordsByArtist));
        assertEquals(record.getId(), recordsByArtist.get(0).getId());
    }

    @Test
    void testDeleteByArtist() {
        Artist artist = artist();
        artistRepository.insert(artist);

        Record record = record(artist);
        recordDao.insert(record);

        List<Record> recordsByArtist = recordDao.findByArtist(artist.getId());
        assertTrue(isNotEmpty(recordsByArtist));
        assertEquals(record.getId(), recordsByArtist.get(0).getId());

        recordDao.deleteByArtistId(artist.getId());
        assertTrue(isEmpty(recordDao.findByArtist(artist.getId())));
    }
}
