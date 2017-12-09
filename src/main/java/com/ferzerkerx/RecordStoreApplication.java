package com.ferzerkerx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@SpringBootApplication
public class RecordStoreApplication {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("simple-jpa");
    }


    public static void main(String[] args) {
        SpringApplication.run(RecordStoreApplication.class, args);
    }

}
