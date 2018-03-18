package com.ferzerkerx.controller;

import com.ferzerkerx.model.Artist;
import com.ferzerkerx.service.RecordStoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.ferzerkerx.TestUtils.*;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ArtistController.class)
class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecordStoreService recordStoreService;

    @Test
    void showAllArtists() throws Exception {
        when(recordStoreService.findAllArtists()).thenReturn(singletonList(artist()));

        this.mockMvc.perform(
                get("/artists"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resource("list-of-artist-response.json"), true));
    }

    @Test
    void saveArtist() throws Exception {
        ArgumentCaptor<Artist> artistCaptor = ArgumentCaptor.forClass(Artist.class);
        doNothing().when(recordStoreService).saveArtist(artistCaptor.capture());

        this.mockMvc.perform(
                post("/artist")
                        .content(resource("artist-request.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resource("artist-response.json"), true));

        Artist captureValue = artistCaptor.getValue();
        assertEquals("foo", captureValue.getName());
    }

    @Test
    void findArtistById() throws Exception {
        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        doReturn(artist()).when(recordStoreService).findArtistById(idCaptor.capture());

        this.mockMvc.perform(
                get("/artist/10"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resource("artist-response.json"), true));

        int captureValue = idCaptor.getValue();
        assertEquals(10, captureValue);
    }

    @Test
    void deleteArtistById() throws Exception {
        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(recordStoreService).deleteArtistWithRecordsById(idCaptor.capture());

        this.mockMvc.perform(
                delete("/artist/10"))
                .andExpect(status().isOk());

        int captureValue = idCaptor.getValue();
        assertEquals(10, captureValue);
    }

    @Test
    void updateArtistById() throws Exception {
        ArgumentCaptor<Artist> artistCaptor = ArgumentCaptor.forClass(Artist.class);
        doAnswer(invocationOnMock -> artistCaptor.getValue())
                .when(recordStoreService).updateArtistById(artistCaptor.capture());


        this.mockMvc.perform(
                put("/artist/10")
                        .content(resource("artist-request.json"))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resource("update-artist-response.json"), true));

        Artist captureValue = artistCaptor.getValue();
        assertEquals(10, captureValue.getId());
        assertEquals("foo", captureValue.getName());

    }

    @Test
    void findMatchedArtistsByName() throws Exception {
        doReturn(singletonList(artist()))
                .when(recordStoreService).findMatchedArtistsByName("someName");

        this.mockMvc.perform(
                get("/artist/search")
                        .param("name", "someName"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resource("list-of-artist-response.json"), true));

    }
}