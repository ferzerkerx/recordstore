package com.ferzerkerx.dao;

import java.util.Collection;
import java.util.List;
import com.ferzerkerx.model.Artist;

public interface ArtistDao extends BaseDao<Artist> {
    Collection<Artist> findAllArtists();

    List<Artist> findMatchedArtistsByName(String name);
}
