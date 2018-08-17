package com.spidermanteam.spiderpuppies.data;

import com.spidermanteam.spiderpuppies.data.base.AdminRepository;
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
public class AdminRepositoryImpl implements AdminRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public AdminRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void addAdmin(Admin admin) {
        User user = admin.getUser();
        Authorities authorities = new Authorities(user.getUsername(),"ROLE_ADMIN");
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(user);
            session.save(authorities);
            session.save(admin);
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List listAllAdmins() {
        List admins = new ArrayList<>();
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            admins = session.createQuery("from Admin ").list();
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return admins;
    }
}
