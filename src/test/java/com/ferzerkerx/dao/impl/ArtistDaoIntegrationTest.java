package com.ferzerkerx.dao.impl;

import com.ferzerkerx.dao.ArtistDao;
import com.ferzerkerx.model.Artist;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ArtistDaoIntegrationTest extends BaseDaoIntegrationTest {

    @Autowired
    private ArtistDao artistDao;

    @Before
    public void setUp() {
    }

    @Test
    public void testInsert() {
        Artist newArtist = new Artist();
        newArtist.setName("name");

        artistDao.insert(newArtist);

        Artist artistFromDb = artistDao.findById(newArtist.getId());

        assertNotNull(artistFromDb);
        assertEquals(newArtist.getId(), artistFromDb.getId());
    }

    @Test
    public void testUpdate() {
        Artist updatedArtist = new Artist();
        updatedArtist.setName("foo");
        artistDao.insert(updatedArtist);

        updatedArtist.setName("bar");
        artistDao.update(updatedArtist);

        Artist dbArtist = artistDao.findById(updatedArtist.getId());
        assertNotNull(dbArtist);
        assertEquals("bar", dbArtist.getName());
    }

    @Test
    public void testDelete() {
        Artist artist = new Artist();
        artist.setName("foo");
        artistDao.insert(artist);

        Integer artisId = artist.getId();
        artistDao.delete(artist);

        Artist dbArtist = artistDao.findById(artisId);
        assertNull(dbArtist);
    }

    @Test
    public void testFindMatchedArtistsByName() {
        Artist artist = new Artist();
        artist.setName("foo bar");
        artistDao.insert(artist);

        List<Artist> fooLikeArtist = artistDao.findMatchedArtistsByName("foo");
        assertTrue(CollectionUtils.isNotEmpty(fooLikeArtist));

        List<Artist> zooLikeArtist = artistDao.findMatchedArtistsByName("zoo");
        assertTrue(CollectionUtils.isEmpty(zooLikeArtist));
    }
}
