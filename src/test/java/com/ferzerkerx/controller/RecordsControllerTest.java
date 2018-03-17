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

import static com.ferzerkerx.TestUtils.defaultRecord;
import static com.ferzerkerx.TestUtils.resource;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        when(recordStoreService.findRecordsByArtist(artistId)).thenReturn(singletonList(defaultRecord()));

        this.mockMvc.perform(
                get("/artist/3/records"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resource("list-of-record-response.json"), true));
    }

    @Test
    void saveRecord() throws Exception {
        ArgumentCaptor<Record> recordCaptor = ArgumentCaptor.forClass(Record.class);
        doNothing().when(recordStoreService).saveRecord(eq(artistId), recordCaptor.capture());

        this.mockMvc.perform(
                post("/artist/3/record")
                        .content(resource("record-request.json"))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resource("record-response.json"), true));

        Record captureValue = recordCaptor.getValue();
        assertEquals("title", captureValue.getTitle());
        assertEquals("2004", captureValue.getYear());
    }

    @Test
    void findRecordById() throws Exception {
        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        doReturn(defaultRecord()).when(recordStoreService).findRecordById(idCaptor.capture());

        this.mockMvc.perform(
                get("/record/10"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resource("record-response.json"), true));

        int captureValue = idCaptor.getValue();
        assertEquals(10, captureValue);

    }

    @Test
    void deleteRecordById() throws Exception {
        ArgumentCaptor<Integer> idCaptor = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(recordStoreService).deleteRecordById(idCaptor.capture());

        this.mockMvc.perform(
                delete("/record/10"))
                .andExpect(status().isOk());

        int captureValue = idCaptor.getValue();
        assertEquals(10, captureValue);

    }

    @Test
    void updateArtistById() throws Exception {
        ArgumentCaptor<Record> recordCaptor = ArgumentCaptor.forClass(Record.class);
        doAnswer(invocationOnMock -> recordCaptor.getValue())
                .when(recordStoreService).updateRecordById(recordCaptor.capture());


        this.mockMvc.perform(
                put("/record/10")
                        .content(resource("record-request.json"))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resource("update-record-response.json"), true));

        Record captureValue = recordCaptor.getValue();
        assertEquals(10, captureValue.getId());
        assertEquals("title", captureValue.getTitle());
        assertEquals("2004", captureValue.getYear());
    }

    @Test
    void findMatchedRecordByCriteria() throws Exception {
        doReturn(singletonList(defaultRecord()))
                .when(recordStoreService).findMatchedRecordByCriteria("someTitle", "someYear");

        this.mockMvc.perform(
                get("/records/search")
                        .param("title", "someTitle")
                        .param("year", "someYear")
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(resource("list-of-record-response.json"), true));

    }
}