package com.ferzerkerx.repository;

import java.util.List;

import com.ferzerkerx.model.Artist;

public interface ArtistRepository extends BaseDao<Artist> {

    List<Artist> findAllArtists();

    List<Artist> findMatchedArtistsByName(String name);
}
