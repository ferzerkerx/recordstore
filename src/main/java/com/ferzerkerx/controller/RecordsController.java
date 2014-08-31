package com.ferzerkerx.controller;

import java.util.List;
import com.ferzerkerx.model.Record;
import com.ferzerkerx.service.RecordStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
