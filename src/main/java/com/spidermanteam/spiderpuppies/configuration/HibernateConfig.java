package com.spidermanteam.spiderpuppies.configuration;

import com.spidermanteam.spiderpuppies.models.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HibernateConfig {

  @Bean
  public SessionFactory createSessionFactory() {
    return new org.hibernate.cfg.Configuration()
        .configure("hibernate.cfg.xml")
        .addAnnotatedClass(Admin.class)
        .addAnnotatedClass(Authorities.class)
        .addAnnotatedClass(Client.class)
        .addAnnotatedClass(Invoice.class)
        .addAnnotatedClass(Subscriber.class)
        .addAnnotatedClass(TelecomService.class)
        .addAnnotatedClass(User.class)
        .buildSessionFactory();
  }


}
