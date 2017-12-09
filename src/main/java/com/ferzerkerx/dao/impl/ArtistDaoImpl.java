package com.ferzerkerx.dao.impl;

import com.ferzerkerx.dao.ArtistDao;
import com.ferzerkerx.model.Artist;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ArtistDaoImpl extends BaseDaoImpl<Artist> implements ArtistDao {

    public ArtistDaoImpl() {
        super(Artist.class);
    }

    @Override
    public List<Artist> findAllArtists() {
        TypedQuery<Artist> query = createQuery("SELECT e FROM Artist e");
        return query.getResultList();
    }

    @Override
    public List<Artist> findMatchedArtistsByName(String name) {
        TypedQuery<Artist> query = createQuery("SELECT e FROM Artist e WHERE e.name LIKE :name");
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
}
