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

    @RequestMapping(value = {"/artist/{id}/records"}, method = RequestMethod.GET)
    public List<Record> showRecordsForArtist(@PathVariable(value = "id") int artistId) {
        return recordStoreService.findRecordsByArtist(artistId);
    }

    @RequestMapping(value = {"/artist/{id}/record"}, method = RequestMethod.POST)
    public Record saveRecord(@PathVariable(value = "id") int artistId, @RequestBody Record record) {
        recordStoreService.saveRecord(artistId, record);
        return record;
    }

    @RequestMapping(value = {"/record/{id}"}, method = RequestMethod.GET)
    public Record findRecordById(@PathVariable(value = "id") int recordId) {
        return recordStoreService.findRecordById(recordId);
    }

    @RequestMapping(value = {"/record/{id}"}, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecordById(@PathVariable(value = "id") int recordId) {
        recordStoreService.deleteRecordById(recordId);
    }

    @RequestMapping(value = {"/record/{id}"}, method = RequestMethod.PUT)
    public Record updateArtistById(@PathVariable(value = "id") int recordId, @RequestBody Record record) {
        record.setId(recordId);
        return recordStoreService.updateRecordById(record);
    }

    @RequestMapping(value = {"/records/search"}, method = RequestMethod.GET)
    public List<Record> findMatchedRecordByCriteria(@RequestParam(value = "title", required = false) String title, @RequestParam(value = "year", required = false) String year) {
        if (StringUtils.isBlank(title) && StringUtils.isBlank(year)) {
            throw new BaseException("At least one criteria must be specified");
        }
        return recordStoreService.findMatchedRecordByCriteria(title, year);
    }
}
