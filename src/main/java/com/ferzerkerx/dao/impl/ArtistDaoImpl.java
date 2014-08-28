package com.ferzerkerx.dao.impl;

import javax.persistence.Query;
import java.util.Collection;
import com.ferzerkerx.dao.ArtistDao;
import com.ferzerkerx.model.Artist;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistDaoImpl extends BaseDaoImpl<Artist> implements ArtistDao {

    @Override
    public Collection<Artist> findAllArtists() {
        Query query = getEm().createQuery("SELECT e FROM Artist e");
        return (Collection<Artist>) query.getResultList();
    }
}
