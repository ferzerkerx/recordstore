package com.ferzerkerx;

import com.ferzerkerx.filter.SimpleCORSFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@SpringBootApplication
public class RecordStoreApplication {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("simple-jpa");
    }

    @Bean
    public FilterRegistrationBean corsFilterRegistration() {
        FilterRegistrationBean<SimpleCORSFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(corsFilter());
        registration.addUrlPatterns("*");
        registration.setName("corsFilter");
        registration.setOrder(1);
        return registration;
    }

    private SimpleCORSFilter corsFilter() {
        return new SimpleCORSFilter();
    }


    public static void main(String[] args) {
        SpringApplication.run(RecordStoreApplication.class, args);
    }

}
