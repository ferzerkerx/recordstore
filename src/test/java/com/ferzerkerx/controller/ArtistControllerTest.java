package com.ferzerkerx.controller;

import com.ferzerkerx.model.Artist;
import junit.framework.TestCase;
import org.codehaus.jackson.map.ObjectMapper;

public class ArtistControllerTest extends TestCase {

    public void testSaveArtist() throws Exception {

        String json = "{\"id\":10,\"name\":\"ELUVEITIE\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        Artist artist = objectMapper.readValue(json, Artist.class);
        assertNotNull(artist);
    }
}