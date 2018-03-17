package com.ferzerkerx.repository.impl;

import com.ferzerkerx.repository.ArtistRepository;
import com.ferzerkerx.repository.RecordDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@EnableTransactionManagement
public class TestDbConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("simple-jpa");
    }

    @Bean
    public ArtistRepository artistDao(EntityManagerFactory factory) {
        ArtistRepositoryImpl artistDao = new ArtistRepositoryImpl();
        artistDao.setEm(factory.createEntityManager());
        return artistDao;
    }

    @Bean
    public RecordDao recordDao(EntityManagerFactory factory) {
        RecordRepositorympl recordDao = new RecordRepositorympl();
        recordDao.setEm(factory.createEntityManager());
        return recordDao;
    }

    @Bean
    public JpaTransactionManager transactionManager(final EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
