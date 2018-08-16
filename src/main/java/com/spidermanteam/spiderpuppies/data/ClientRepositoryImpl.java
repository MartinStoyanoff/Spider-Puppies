package com.spidermanteam.spiderpuppies.data;

import com.spidermanteam.spiderpuppies.data.base.ClientRepository;
import com.spidermanteam.spiderpuppies.models.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public ClientRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addClient(Client client) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(client);
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List listAllClients() {
        List clients = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            clients = session.createQuery("from Client ").list();
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return clients;
    }
}
