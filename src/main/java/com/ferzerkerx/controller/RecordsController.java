package com.ferzerkerx.controller;

import java.util.List;
import com.ferzerkerx.model.BaseException;
import com.ferzerkerx.model.Record;
import com.ferzerkerx.service.RecordStoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class RecordsController {

    @Autowired
    private RecordStoreService recordStoreService;

    @RequestMapping(value = {"/artist/{id}/records.json"}, method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
    @ResponseBody
    public List<Record> showRecordsForArtist(@PathVariable(value = "id") int artistId) {
        return recordStoreService.findRecordsByArtist(artistId);
    }

    @RequestMapping(value = {"/artist/{id}/record.json"}, method = RequestMethod.POST, headers = "Accept=application/json", produces = { "application/json" })
    @ResponseBody
    public Record saveRecord(@PathVariable(value = "id") int artistId, @RequestBody Record record) {
        recordStoreService.saveRecord(artistId, record);
        return record;
    }

    @RequestMapping(value = {"/record/{id}.json"}, method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
    @ResponseBody
    public Record findRecordById(@PathVariable(value = "id") int recordId) {
        return recordStoreService.findRecordById(recordId);
    }

    @RequestMapping(value = {"/record/{id}.json"}, method = RequestMethod.DELETE, headers = "Accept=application/json", produces = { "application/json" })
    @ResponseStatus(HttpStatus.OK)
    public void deleteRecordById(@PathVariable(value = "id") int recordId) {
        recordStoreService.deleteRecordById(recordId);
    }

    @RequestMapping(value = {"/record/{id}.json"}, method = RequestMethod.PUT, headers = "Accept=application/json", produces = { "application/json" })
    @ResponseBody
    public Record updateArtistById(@PathVariable(value = "id") int recordId, @RequestBody Record record) {
        record.setId(recordId);
        return recordStoreService.updateRecordById(record);
    }

    @RequestMapping(value = {"/records/search.json"}, method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
    @ResponseBody
    public List<Record> findMatchedRecordByCriteria(@RequestParam(value = "title", required = false) String title, @RequestParam(value = "year", required = false) String year) {
        if (StringUtils.isBlank(title) && StringUtils.isBlank(year)) {
            throw new BaseException("At least one criteria must be specified");
        }
        return recordStoreService.findMatchedRecordByCriteria(title, year);
    }
}
