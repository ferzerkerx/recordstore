package com.ferzerkerx.dao.impl;

import com.ferzerkerx.dao.ArtistDao;
import com.ferzerkerx.model.Artist;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ArtistDaoTest extends BaseDaoImplTest {

    @Autowired
    private ArtistDao artistDao;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testInsert() throws Exception {
        Artist newArtist = new Artist();
        newArtist.setName("name");

        artistDao.insert(newArtist);

        Artist artistFromDb = artistDao.findById(Artist.class, newArtist.getId());

        assertNotNull(artistFromDb);
        assertEquals(newArtist.getId(), artistFromDb.getId());
    }
}
