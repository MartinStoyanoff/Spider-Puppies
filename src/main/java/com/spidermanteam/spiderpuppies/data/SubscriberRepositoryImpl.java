package com.spidermanteam.spiderpuppies.data;

import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.models.Authorities;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class SubscriberRepositoryImpl implements GenericRepository<Subscriber> {
    private SessionFactory sessionFactory;

    @Autowired
    public SubscriberRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void create(Subscriber subscriber) {
        
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(subscriber);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Subscriber findById(int id) {
        Subscriber subscriber = new Subscriber();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            subscriber = session.get(Subscriber.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return subscriber;
    }

    @Override
    public List<Subscriber> listAll() {
        List<Subscriber> subscribers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            subscribers = session.createQuery("from Subscriber").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return subscribers;
    }


    @Override
    public void update(Subscriber subscriber) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(subscriber);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Subscriber subscriber = session.get(Subscriber.class, id);
            session.delete(subscriber);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
