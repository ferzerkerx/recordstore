package com.ferzerkerx.dao.impl;

import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import com.ferzerkerx.dao.ArtistDao;
import com.ferzerkerx.model.Artist;
import org.springframework.stereotype.Repository;

@Repository
public class ArtistDaoImpl extends BaseDaoImpl<Artist> implements ArtistDao {

    public ArtistDaoImpl() {
        super(Artist.class);
    }

    @Override
    public Collection<Artist> findAllArtists() {
        Query query = getEm().createQuery("SELECT e FROM Artist e");
        return (Collection<Artist>) query.getResultList();
    }

    @Override
    public List<Artist> findMatchedArtistsByName(String name) {
        Query query = getEm().createQuery("SELECT e FROM Artist e WHERE e.name LIKE :name");
        query.setParameter("name", "%" + name + "%");
        return (List<Artist>) query.getResultList();
    }
}
