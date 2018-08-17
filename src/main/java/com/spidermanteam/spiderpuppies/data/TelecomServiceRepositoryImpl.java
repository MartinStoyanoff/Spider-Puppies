package com.spidermanteam.spiderpuppies.data;

import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.models.TelecomService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TelecomServiceRepositoryImpl implements GenericRepository<TelecomService> {

    private SessionFactory sessionFactory;


    @Autowired
    public TelecomServiceRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<TelecomService> listAll() {
        List<TelecomService> telecomServicess = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            telecomServicess = session.createQuery("from TelecomService ").list();
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return telecomServicess;
    }

    @Override
    public TelecomService findById(int id) {
        TelecomService telecomService = new TelecomService();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            telecomService = session.get(TelecomService.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return telecomService;
    }

    @Override
    public void create(TelecomService telecomService) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(telecomService);
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(TelecomService telecomService) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(telecomService);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            TelecomService telecomService = session.get(TelecomService.class, id);
            session.delete(telecomService);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
