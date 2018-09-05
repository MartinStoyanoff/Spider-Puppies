package com.spidermanteam.spiderpuppies.data;

import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.models.User;
import com.spidermanteam.spiderpuppies.models.Authorities;
import com.spidermanteam.spiderpuppies.models.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepositoryImpl implements GenericRepository<Client> {

    private SessionFactory sessionFactory;

    @Autowired
    public ClientRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Client client) {
        User user = client.getUser();
        Authorities authorities = new Authorities(user.getUsername(), "ROLE_CLIENT");
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.save(authorities);
            session.save(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Client findById(int id) {
        Client client = new Client();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            client = session.get(Client.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return client;
    }

    @Override
    public List<Client> listAll() {
        List<Client> clients = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            clients = session.createQuery("from Client").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return clients;
    }


    @Override
    public void update(Client client) {
        User oldUser = null ;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Client client1 = session.get(Client.class, client.getId());
            oldUser = client1.getUser();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Authorities oldAuthority = new Authorities(oldUser.getUsername(), "ROLE_CLIENT");
            Authorities newAuthority = new Authorities(client.getUser().getUsername(), "ROLE_CLIENT");
            User user = client.getUser();
            user.setId(oldUser.getId());
            user.setPassword(oldUser.getPassword());
            session.delete(oldAuthority);
            session.save(newAuthority);
            session.update(user);
            session.update(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Client client = session.get(Client.class, id);
            User user = client.getUser();
            Authorities authority = new Authorities(user.getUsername(),"ROLE_CLIENT");
            session.delete(client);
            session.delete(authority);
            session.delete(user);
            session.delete(client);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
