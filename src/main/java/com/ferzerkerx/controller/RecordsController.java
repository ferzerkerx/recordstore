package com.ferzerkerx.controller;

import com.ferzerkerx.model.ApplicationException;
import com.ferzerkerx.model.Record;
import com.ferzerkerx.service.RecordStoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecordsController {

    private final RecordStoreService recordStoreService;

    @Autowired
    public RecordsController(RecordStoreService recordStoreService) {
        this.recordStoreService = recordStoreService;
    }

    @GetMapping(value = {"/artist/{id}/records"})
    public List<Record> showRecordsForArtist(@PathVariable(value = "id") int artistId) {
        return recordStoreService.findRecordsByArtist(artistId);
    }

    @PostMapping(value = {"/artist/{id}/record"})
    public Record saveRecord(@PathVariable(value = "id") int artistId, @RequestBody RecordDto recordDto) {
        Record record = recordDto.toEntity();
        recordStoreService.saveRecord(artistId, record);
        return record;
    }

    @GetMapping(value = {"/record/{id}"})
    public Record findRecordById(@PathVariable(value = "id") int recordId) {
        return recordStoreService.findRecordById(recordId);
    }

    @DeleteMapping(value = {"/record/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecordById(@PathVariable(value = "id") int recordId) {
        recordStoreService.deleteRecordById(recordId);
    }

    @PutMapping(value = {"/record/{id}"})
    public Record updateArtistById(@PathVariable(value = "id") int recordId, @RequestBody RecordDto recordDto) {
        recordDto.setId(recordId);
        return recordStoreService.updateRecordById(recordDto.toEntity());
    }

    @GetMapping(value = {"/records/search"})
    public List<Record> findMatchedRecordByCriteria(@RequestParam(value = "title", required = false) String title, @RequestParam(value = "year", required = false) String year) {
        if (StringUtils.isBlank(title) && StringUtils.isBlank(year)) {
            throw new ApplicationException("At least one criteria must be specified");
        }
        return recordStoreService.findMatchedRecordByCriteria(title, year);
    }

    static class RecordDto {
        private int id;
        private String title;
        private String year;

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setYear(String year) {
            this.year = year;
        }

        Record toEntity() {
            Record record = new Record();
            record.setId(id);
            record.setTitle(title);
            record.setYear(year);
            return record;
        }
    }
}
