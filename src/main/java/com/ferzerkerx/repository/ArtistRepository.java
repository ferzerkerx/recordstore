package com.ferzerkerx.repository;

import com.ferzerkerx.model.Artist;

import java.util.List;

public interface ArtistRepository extends BaseDao<Artist> {

    List<Artist> findAllArtists();

    List<Artist> findMatchedArtistsByName(String name);

    List<Artist> findMatchedArtistsByNameWithRecords(String name);
}
