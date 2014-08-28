package com.ferzerkerx.controller;

import java.util.List;
import com.ferzerkerx.model.Record;
import com.ferzerkerx.service.RecordStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RecordsController {


    @Autowired
    private RecordStoreService recordStoreService;

    @RequestMapping(value = {"/records/artist/{id}.json"}, method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
    @ResponseBody
    public List<Record> showAllArtists(@PathVariable(value = "id") int artistId) {
        return recordStoreService.findRecordsByArtist(artistId);
    }
}
