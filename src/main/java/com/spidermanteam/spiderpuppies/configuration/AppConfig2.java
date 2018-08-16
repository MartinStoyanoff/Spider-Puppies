package com.spidermanteam.spiderpuppies.configuration;

import com.spidermanteam.spiderpuppies.models.Authorities;
import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.models.User;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppConfig2 {

    @Bean
    public SessionFactory createSessionFactory(){
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
               .addAnnotatedClass(Authorities.class)
                .addAnnotatedClass(Client.class)
                .buildSessionFactory();
    }

}
