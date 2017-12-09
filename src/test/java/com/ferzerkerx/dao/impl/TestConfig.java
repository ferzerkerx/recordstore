package com.ferzerkerx.dao.impl;

import com.ferzerkerx.dao.ArtistDao;
import com.ferzerkerx.dao.RecordDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@EnableTransactionManagement
public class TestConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("simple-jpa");
    }

    @Bean
    public ArtistDao artistDao(EntityManagerFactory factory) {
        ArtistDaoImpl artistDao = new ArtistDaoImpl();
        artistDao.setEm(factory.createEntityManager());
        return artistDao;
    }

    @Bean
    public RecordDao recordDao(EntityManagerFactory factory) {
        RecordDaoImpl recordDao = new RecordDaoImpl();
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
