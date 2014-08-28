package com.ferzerkerx.dao.impl;

import com.ferzerkerx.dao.ArtistDao;
import com.ferzerkerx.dao.RecordDao;
import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

        Record recordFromDb = recordDao.findById(Record.class, newRecord.getId());

        assertNotNull(recordFromDb);
        assertEquals(newRecord.getId(), recordFromDb.getId());
        assertEquals(newRecord.getTitle(), recordFromDb.getTitle());
        assertEquals(newRecord.getYear(), recordFromDb.getYear());
    }
}
