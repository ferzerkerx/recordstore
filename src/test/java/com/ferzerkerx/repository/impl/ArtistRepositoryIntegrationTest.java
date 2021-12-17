package com.ferzerkerx.repository.impl;

import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;
import com.ferzerkerx.repository.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;
import static org.apache.commons.collections4.IterableUtils.isEmpty;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestPropertySource(properties = "spring.datasource.initialization-mode=never")
class ArtistRepositoryIntegrationTest {

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
    void testFindArtistsByName() {
        Artist artist = new Artist();
        artist.setName("foo bar");
        artistRepository.insert(artist);

        List<Artist> fooLikeArtist = artistRepository.findArtistsByName("foo");
        assertTrue(isNotEmpty(fooLikeArtist));
        List<Record> records = artist.getRecords();
        assertTrue(isEmpty(records));

        List<Artist> zooLikeArtist = artistRepository.findArtistsByName("zoo");
        assertTrue(isEmpty(zooLikeArtist));
    }

    @Test
    void testFindArtistsByNameWithRecords() {
        Artist artist = new Artist();
        artist.setName("baz");
        artist.setRecords(Collections.emptyList());
        artistRepository.insert(artist);

        List<Artist> fooLikeArtist = artistRepository.findArtistsByNameWithRecords("baz");
        assertTrue(isNotEmpty(fooLikeArtist));

        List<Artist> zooLikeArtist = artistRepository.findArtistsByName("zoo");
        assertTrue(isEmpty(zooLikeArtist));
    }

    @TestConfiguration
    static class LocalConfiguration {
        @Bean
        public ArtistRepository artistRepository(EntityManager entityManager) {
            return new ArtistRepositoryImpl(entityManager);
        }
    }
}
