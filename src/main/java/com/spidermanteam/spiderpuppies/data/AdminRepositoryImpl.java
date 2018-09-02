package com.spidermanteam.spiderpuppies.data;

import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.models.Admin;
import com.spidermanteam.spiderpuppies.models.Authorities;
import com.spidermanteam.spiderpuppies.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdminRepositoryImpl implements GenericRepository<Admin> {

    private SessionFactory sessionFactory;

    @Autowired
    public AdminRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Admin admin) {
        User user = admin.getUser();
        Authorities authorities = new Authorities(user.getUsername(), "ROLE_ADMIN");
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.save(authorities);
            session.save(admin);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Admin findById(int id) {
        Admin admin = new Admin();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            admin = session.get(Admin.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return admin;
    }

    @Override
    public List<Admin> listAll() {
        List<Admin> admins = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            admins = session.createQuery("from Admin").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return admins;
    }

    @Override
    public void update(Admin admin) {
        User oldUser = null ;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Admin admin1 = session.get(Admin.class, admin.getId());
            oldUser = admin1.getUser();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = admin.getUser();
            Authorities oldAuthority = new Authorities(oldUser.getUsername(), "ROLE_ADMIN");
            Authorities newAuthority = new Authorities(admin.getUser().getUsername(), "ROLE_ADMIN");
            user.setPassword(oldUser.getPassword());
            user.setId(oldUser.getId());
            session.delete(oldAuthority);
            session.save(newAuthority);
            session.update(user);
            session.update(admin);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Admin admin = session.get(Admin.class, id);
            User user = admin.getUser();
            Authorities authority = new Authorities(user.getUsername(),"ROLE_ADMIN");
            session.delete(admin);
            session.delete(authority);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
