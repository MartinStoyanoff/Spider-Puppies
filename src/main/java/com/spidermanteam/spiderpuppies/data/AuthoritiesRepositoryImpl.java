package com.spidermanteam.spiderpuppies.data;

import com.spidermanteam.spiderpuppies.data.base.AuthoritiesRepository;
import com.spidermanteam.spiderpuppies.models.Authorities;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthoritiesRepositoryImpl implements AuthoritiesRepository {

  private SessionFactory sessionFactory;

  @Autowired
  public AuthoritiesRepositoryImpl(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public Authorities getAuthoritiesByUserName(String username) {
    Authorities authorities = new Authorities();
    List<Authorities> authoritiesList = new ArrayList<>();
    try (Session session = sessionFactory.openSession()) {
      session.beginTransaction();
      String query = "from Authorities as а where а.username=:username";
      authoritiesList = session.createQuery(query)
          .setParameter("username", username).list();
      authorities = authoritiesList.get(0);
      session.getTransaction().commit();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return authorities;
  }
}
