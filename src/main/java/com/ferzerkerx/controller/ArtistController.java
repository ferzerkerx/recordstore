package com.ferzerkerx.controller;

import com.ferzerkerx.model.Artist;
import com.ferzerkerx.service.RecordStoreService;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@Timed
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
    public Artist saveArtist(@RequestBody ArtistDto artistDto) {
        Artist artist = artistDto.toEntity();
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
    public Artist updateArtistById(@PathVariable(value = "id") int artistId, @RequestBody ArtistDto artistDto) {
        artistDto.setId(artistId);
        return recordStoreService.updateArtistById(artistDto.toEntity());
    }

    @GetMapping(value = {"/artist/search"})
    public List<Artist> findMatchedArtistsByName(@RequestParam(value = "name") String name) {
        return recordStoreService.findMatchedArtistsByName(name);
    }

    static class ArtistDto {
        private int id;
        private String name;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        Artist toEntity() {
            Artist artist = new Artist();
            artist.setId(id);
            artist.setName(name);
            return artist;
        }
    }

}
