package com.ferzerkerx.dao;

import java.util.Collection;
import com.ferzerkerx.model.Artist;

public interface ArtistDao extends BaseDao<Artist> {
    Collection<Artist> findAllArtists();
}
