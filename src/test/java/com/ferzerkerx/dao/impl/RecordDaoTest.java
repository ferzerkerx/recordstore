package com.ferzerkerx.dao.impl;

import java.util.List;
import com.ferzerkerx.dao.ArtistDao;
import com.ferzerkerx.dao.RecordDao;
import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class RecordDaoTest extends BaseDaoImplTest {

    @Autowired
    private RecordDao recordDao;


    @Autowired
    private ArtistDao artistDao;

    @Test
    public void testInsert() throws Exception {
        Artist artist = new Artist();
        artist.setName("foo");
        artistDao.insert(artist);

        Record newRecord = new Record();
        newRecord.setArtist(artist);
        newRecord.setTitle("title");
        newRecord.setYear("2004");
        recordDao.insert(newRecord);

        Record recordFromDb = recordDao.findById(newRecord.getId());

        assertNotNull(recordFromDb);
        assertEquals(newRecord.getId(), recordFromDb.getId());
        assertEquals(newRecord.getTitle(), recordFromDb.getTitle());
        assertEquals(newRecord.getYear(), recordFromDb.getYear());
    }

    @Test
    public void testUpdate() throws Exception {
        Artist artist = new Artist();
        artist.setName("foo");
        artistDao.insert(artist);

        Record updatedRecord = new Record();
        updatedRecord.setArtist(artist);
        updatedRecord.setTitle("title");
        updatedRecord.setYear("2004");
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
    public void testDelete() throws Exception {
        Artist artist = new Artist();
        artist.setName("foo");
        artistDao.insert(artist);

        Record record = new Record();
        record.setArtist(artist);
        record.setTitle("title");
        record.setYear("2004");
        recordDao.insert(record);

        Integer recordId = record.getId();
        recordDao.delete(record);

        Record dbRecord = recordDao.findById(recordId);
        assertNull(dbRecord);
    }

    @Test
    public void testFindRecordsByArtist() throws Exception {
        Artist artist = new Artist();
        artist.setName("foo");
        artistDao.insert(artist);

        Record record = new Record();
        record.setArtist(artist);
        record.setTitle("title");
        record.setYear("2004");
        recordDao.insert(record);

        List<Record> recordsByArtist = recordDao.findRecordsByArtist(artist.getId());
        assertTrue(CollectionUtils.isNotEmpty(recordsByArtist));
        assertEquals(record.getId(), recordsByArtist.get(0).getId());
    }
}
