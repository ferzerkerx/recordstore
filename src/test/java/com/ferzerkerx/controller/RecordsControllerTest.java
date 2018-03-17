package com.ferzerkerx.controller;

import com.ferzerkerx.model.Record;
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

import java.util.Collections;

import static com.ferzerkerx.TestUtils.defaultRecord;
import static com.ferzerkerx.TestUtils.resource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(RecordsController.class)
class RecordsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecordStoreService recordStoreService;
    private final int artistId = 3;

    @Test
    void showRecordsForArtist() throws Exception {
        when(recordStoreService.findRecordsByArtist(artistId)).thenReturn(Collections.singletonList(defaultRecord()));

        this.mockMvc.perform(
                get("/artist/3/records"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resource("show-record-response.json"), true));
    }

    @Test
    void saveRecord() throws Exception {
        ArgumentCaptor<Record> recordCapture = ArgumentCaptor.forClass(Record.class);
        doNothing().when(recordStoreService).saveRecord(eq(artistId), recordCapture.capture());

        this.mockMvc.perform(
                post("/artist/3/record")
                    .content(resource("record-request.json"))
                    .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resource("record-response.json"), true));

        Record captureValue = recordCapture.getValue();
        assertEquals(captureValue.getTitle(), "title");
        assertEquals(captureValue.getYear(), "2010");
    }

    @Test
    void findRecordById() {
    }

    @Test
    void deleteRecordById() {
    }

    @Test
    void updateArtistById() {
    }

    @Test
    void findMatchedRecordByCriteria() {
    }
}