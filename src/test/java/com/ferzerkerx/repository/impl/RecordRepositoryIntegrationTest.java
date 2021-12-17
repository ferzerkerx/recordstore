package com.ferzerkerx.repository.impl;

import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;
import com.ferzerkerx.repository.ArtistRepository;
import com.ferzerkerx.repository.RecordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ferzerkerx.TestUtils.artist;
import static com.ferzerkerx.TestUtils.audioRecord;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;
import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@TestPropertySource(properties = "spring.datasource.initialization-mode=never")
class RecordRepositoryIntegrationTest {

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
        Record audioRecord = insertRecord();

        Integer recordId = audioRecord.getId();
        recordRepository.delete(audioRecord);

        Record dbRecord = recordRepository.findById(recordId);
        assertNull(dbRecord);
    }

    @Test
    void testFindRecordsByArtist() {
        Artist artist = artist();
        artistRepository.insert(artist);

        Record audioRecord = audioRecord(artist);
        recordRepository.insert(audioRecord);

        List<Record> recordsByArtist = recordRepository.findByArtist(artist.getId());
        assertTrue(isNotEmpty(recordsByArtist));
        assertEquals(audioRecord.getId(), recordsByArtist.get(0).getId());
    }

    @Test
    void testFindByCriteria_withFullCriteria() {
        Record audioRecord = insertRecord();

        List<Record> recordsByArtist = recordRepository.findByCriteria(audioRecord);
        assertTrue(isNotEmpty(recordsByArtist));
        assertEquals(audioRecord.getId(), recordsByArtist.get(0).getId());
    }

    @Test
    void testFindByCriteria_withNoCriteria() {
        Record audioRecord = insertRecord();

        List<Record> recordsByArtist = recordRepository.findByCriteria(new Record());
        assertTrue(isNotEmpty(recordsByArtist));
        assertTrue(recordsByArtist.contains(audioRecord));
    }

    @Test
    void testDeleteByArtist() {
        Record audioRecord = insertRecord();
        int artistId = audioRecord.getArtist().getId();

        List<Record> recordsByArtist = recordRepository.findByArtist(artistId);
        assertTrue(isNotEmpty(recordsByArtist));
        assertEquals(audioRecord.getId(), recordsByArtist.get(0).getId());

        recordRepository.deleteByArtistId(artistId);
        assertTrue(isEmpty(recordRepository.findByArtist(artistId)));
    }

    private Record insertRecord() {
        Artist artist = artist();
        artistRepository.insert(artist);

        Record audioRecord = audioRecord(artist);
        recordRepository.insert(audioRecord);
        return audioRecord;
    }

    @TestConfiguration
    static class LocalConfiguration {
        @Bean
        public ArtistRepository artistRepository(EntityManager entityManager) {
            return new ArtistRepositoryImpl(entityManager);
        }

        @Bean
        public RecordRepository recordRepository(EntityManager entityManager) {
            return new RecordRepositoryImpl(entityManager);
        }
    }
}
