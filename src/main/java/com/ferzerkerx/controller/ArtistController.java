package com.ferzerkerx.controller;

import com.ferzerkerx.model.Artist;
import com.ferzerkerx.service.RecordStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class ArtistController {

    @Autowired
    private RecordStoreService recordStoreService;

    @RequestMapping(value = {"/artists.json"}, method = RequestMethod.GET)
    public Collection<Artist> showAllArtists() {
        return recordStoreService.findAllArtists();
    }


    @RequestMapping(value = {"/artists.json"}, method = RequestMethod.POST)
    public Artist saveArtist(@RequestBody Artist artist) {
        recordStoreService.saveArtist(artist);
        return artist;
    }

    @RequestMapping(value = {"/artist/{id}.json"}, method = RequestMethod.GET)
    public Artist findArtistById(@PathVariable(value = "id") int artistId) {
        return recordStoreService.findArtistById(artistId);
    }

    @RequestMapping(value = {"/artist/{id}.json"}, method = RequestMethod.DELETE)
    public void deleteArtistById(@PathVariable(value = "id") int artistId) {
        recordStoreService.deleteArtistWithRecordsById(artistId);
    }

    @RequestMapping(value = {"/artist/{id}.json"}, method = RequestMethod.PUT)
    public Artist updateArtistById(@PathVariable(value = "id") int artistId, @RequestBody Artist artist) {
        artist.setId(artistId);
        return recordStoreService.updateArtistById(artist);
    }

    @RequestMapping(value = {"/artist/search.json"}, method = RequestMethod.GET)
    public List<Artist> findMatchedArtistsByName(@RequestParam(value = "name", required = true) String name) {
        return recordStoreService.findMatchedArtistsByName(name);
    }

}
