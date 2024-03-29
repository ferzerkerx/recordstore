package com.ferzerkerx.repository.impl;

import com.ferzerkerx.model.Artist;
import com.ferzerkerx.repository.ArtistRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ArtistRepositoryImpl extends BaseRepositorympl<Artist> implements ArtistRepository {

    public ArtistRepositoryImpl(EntityManager entityManager) {
        super(Artist.class, entityManager);
    }

    @Override
    public List<Artist> findAllArtists() {
        TypedQuery<Artist> query = createQuery("SELECT e FROM Artist e");
        return query.getResultList();
    }

    @Override
    public List<Artist> findArtistsByName(String name) {
        TypedQuery<Artist> query = createQuery("SELECT e FROM Artist e WHERE e.name LIKE :name");
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public List<Artist> findArtistsByNameWithRecords(String name) {
        TypedQuery<Artist> query = createQuery("SELECT e FROM Artist e LEFT JOIN FETCH e.records WHERE e.name LIKE :name");
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
}
