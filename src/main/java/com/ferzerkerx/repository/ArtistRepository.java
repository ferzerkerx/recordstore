package com.ferzerkerx.repository;

import com.ferzerkerx.model.Artist;

import java.util.List;

public interface ArtistRepository extends BaseRepository<Artist> {

    List<Artist> findAllArtists();

    List<Artist> findArtistsByName(String name);

    List<Artist> findArtistsByNameWithRecords(String name);
}
