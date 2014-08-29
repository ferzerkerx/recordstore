package com.ferzerkerx.controller;

import java.util.Collection;
import java.util.Map;
import com.ferzerkerx.model.Artist;
import com.ferzerkerx.service.RecordStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArtistController {

    @Autowired
    private RecordStoreService recordStoreService;

    @RequestMapping({"/","/home"})
    public ModelAndView showHomePage(Map<String, Object> model) {
        Collection<Artist> allArtists = recordStoreService.findAllArtists();
        return new ModelAndView("home-tile" , "artists", allArtists);
    }

    @RequestMapping(value = {"/artists.json"}, method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
    @ResponseBody
    public Collection<Artist> showAllArtists() {
        return recordStoreService.findAllArtists();
    }


    @RequestMapping(value = {"/artists.json"}, method = RequestMethod.POST, headers = "Accept=application/json", produces = { "application/json" })
    @ResponseBody
    public Artist saveArtist(@RequestBody Artist artist) {
        recordStoreService.saveArtist(artist);
        return artist;
    }

}
