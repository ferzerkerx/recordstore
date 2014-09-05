package com.ferzerkerx.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import com.ferzerkerx.model.Artist;
import com.ferzerkerx.service.RecordStoreService;
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

    @RequestMapping(value = {"/artist/{id}.json"}, method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
    @ResponseBody
    public Artist findArtistById(@PathVariable(value = "id") int artistId) {
        return recordStoreService.findArtistById(artistId);
    }

    @RequestMapping(value = {"/artist/{id}.json"}, method = RequestMethod.DELETE, headers = "Accept=application/json", produces = { "application/json" })
    @ResponseStatus(HttpStatus.OK)
    public void deleteArtistById(@PathVariable(value = "id") int artistId) {
        recordStoreService.deleteArtistWithRecordsById(artistId);
    }

    @RequestMapping(value = {"/artist/{id}.json"}, method = RequestMethod.PUT, headers = "Accept=application/json", produces = { "application/json" })
    @ResponseBody
    public Artist updateArtistById(@PathVariable(value = "id") int artistId, @RequestBody Artist artist) {
        artist.setId(artistId);
        return recordStoreService.updateArtistById(artist);
    }

    @RequestMapping(value = {"/artist/search.json"}, method = RequestMethod.GET, headers = "Accept=application/json", produces = { "application/json" })
    @ResponseBody
    public List<Artist> findMatchedArtistsByName(@RequestParam("name") String name) {
        return recordStoreService.findMatchedArtistsByName(name);
    }

}
