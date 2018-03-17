package com.ferzerkerx.repository.impl;

import com.ferzerkerx.repository.ArtistRepository;
import com.ferzerkerx.model.Artist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.collections4.IterableUtils.isEmpty;
import static org.junit.jupiter.api.Assertions.*;

class ArtistRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    private ArtistRepository artistRepository;

    @Test
    void testInsert() {
        Artist newArtist = new Artist();
        newArtist.setName("name");

        artistRepository.insert(newArtist);

        Artist artistFromDb = artistRepository.findById(newArtist.getId());

        assertNotNull(artistFromDb);
        assertEquals(newArtist.getId(), artistFromDb.getId());
    }

    @Test
    void testUpdate() {
        Artist updatedArtist = new Artist();
        updatedArtist.setName("foo");
        artistRepository.insert(updatedArtist);

        updatedArtist.setName("bar");
        artistRepository.update(updatedArtist);

        Artist dbArtist = artistRepository.findById(updatedArtist.getId());
        assertNotNull(dbArtist);
        assertEquals("bar", dbArtist.getName());
    }

    @Test
    void testDelete() {
        Artist artist = new Artist();
        artist.setName("foo");
        artistRepository.insert(artist);

        Integer artistId = artist.getId();
        artistRepository.delete(artist);

        Artist dbArtist = artistRepository.findById(artistId);
        assertNull(dbArtist);
    }

    @Test
    void testFindMatchedArtistsByName() {
        Artist artist = new Artist();
        artist.setName("foo bar");
        artistRepository.insert(artist);

        List<Artist> fooLikeArtist = artistRepository.findMatchedArtistsByName("foo");
        assertTrue(isNotEmpty(fooLikeArtist));

        List<Artist> zooLikeArtist = artistRepository.findMatchedArtistsByName("zoo");
        assertTrue(isEmpty(zooLikeArtist));
    }
}
