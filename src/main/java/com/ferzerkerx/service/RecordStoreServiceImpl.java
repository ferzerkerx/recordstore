package com.ferzerkerx.service;

import java.util.Collection;
import java.util.List;
import com.ferzerkerx.dao.ArtistDao;
import com.ferzerkerx.dao.RecordDao;
import com.ferzerkerx.model.Artist;
import com.ferzerkerx.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class RecordStoreServiceImpl implements RecordStoreService {

    private final ArtistDao artistDao;
    private final RecordDao recordDao;

    @Autowired
    public RecordStoreServiceImpl(ArtistDao artistDao, RecordDao recordDao) {
        this.artistDao = artistDao;
        this.recordDao = recordDao;
    }

    @Override
    public Collection<Artist> findAllArtists() {
        return artistDao.findAllArtists();
    }

    @Override
    public List<Record> findRecordsByArtist(int artistId) {
        return recordDao.findRecordsByArtist(artistId);
    }
}
