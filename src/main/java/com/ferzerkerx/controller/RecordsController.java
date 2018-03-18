package com.ferzerkerx.controller;

import com.ferzerkerx.model.BaseException;
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
    public Record saveRecord(@PathVariable(value = "id") int artistId, @RequestBody Record record) {
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
    public Record updateArtistById(@PathVariable(value = "id") int recordId, @RequestBody Record record) {
        record.setId(recordId);
        return recordStoreService.updateRecordById(record);
    }

    @GetMapping(value = {"/records/search"})
    public List<Record> findMatchedRecordByCriteria(@RequestParam(value = "title", required = false) String title, @RequestParam(value = "year", required = false) String year) {
        if (StringUtils.isBlank(title) && StringUtils.isBlank(year)) {
            throw new BaseException("At least one criteria must be specified");
        }
        return recordStoreService.findMatchedRecordByCriteria(title, year);
    }
}
