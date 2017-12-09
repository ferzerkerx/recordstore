package com.ferzerkerx.dao;

import java.util.List;

import com.ferzerkerx.model.Artist;

public interface ArtistDao extends BaseDao<Artist> {
    List findAllArtists();

    List<Artist> findMatchedArtistsByName(String name);
}
