package com.ferzerkerx.controller;

import com.ferzerkerx.model.Artist;
import com.ferzerkerx.service.RecordStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class ArtistController {

    private final RecordStoreService recordStoreService;

    @Autowired
    public ArtistController(RecordStoreService recordStoreService) {
        this.recordStoreService = recordStoreService;
    }

    @GetMapping(value = {"/artists"})
    public Collection<Artist> showAllArtists() {
        return recordStoreService.findAllArtists();
    }


    @PostMapping(value = {"/artist"})
    public Artist saveArtist(@RequestBody Artist artist) {
        recordStoreService.saveArtist(artist);
        return artist;
    }

    @GetMapping(value = {"/artist/{id}"})
    public Artist findArtistById(@PathVariable(value = "id") int artistId) {
        return recordStoreService.findArtistById(artistId);
    }

    @DeleteMapping(value = {"/artist/{id}"})
    public void deleteArtistById(@PathVariable(value = "id") int artistId) {
        recordStoreService.deleteArtistWithRecordsById(artistId);
    }

    @PutMapping(value = {"/artist/{id}"})
    public Artist updateArtistById(@PathVariable(value = "id") int artistId, @RequestBody Artist artist) {
        artist.setId(artistId);
        return recordStoreService.updateArtistById(artist);
    }

    @GetMapping(value = {"/artist/search"})
    public List<Artist> findMatchedArtistsByName(@RequestParam(value = "name", required = true) String name) {
        return recordStoreService.findMatchedArtistsByName(name);
    }

}
