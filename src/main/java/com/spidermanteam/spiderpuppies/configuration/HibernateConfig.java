package com.spidermanteam.spiderpuppies.configuration;

import com.spidermanteam.spiderpuppies.models.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory createSessionFactory(){
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Authorities.class)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(TelecomService.class)
                .buildSessionFactory();
    }

}
