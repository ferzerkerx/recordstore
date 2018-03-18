package com.ferzerkerx.repository.impl;

import com.ferzerkerx.repository.ArtistRepository;
import com.ferzerkerx.repository.RecordRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class TestDbConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("simple-jpa");
    }

    @Bean
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public ArtistRepository artistRepository(EntityManager entityManager) {
        ArtistRepositoryImpl artistDao = new ArtistRepositoryImpl();
        artistDao.setEm(entityManager);
        return artistDao;
    }

    @Bean
    public RecordRepository recordRepository(EntityManager entityManager) {
        RecordRepositoryImpl recordDao = new RecordRepositoryImpl();
        recordDao.setEm(entityManager);
        return recordDao;
    }

    @Bean
    public JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
