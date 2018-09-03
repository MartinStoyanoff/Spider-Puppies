package com.spidermanteam.spiderpuppies.data;

import com.spidermanteam.spiderpuppies.data.base.UserRepository;
import com.spidermanteam.spiderpuppies.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUserByUserNameAndPassWord(String username, String password) {
        User user = new User() ;
        List<User> userList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "from User as u where u.username=:username and u.password=:password";
            userList = session.createQuery(query)
                    .setParameter("username", username)
                    .setParameter("password",password).list();
            user = userList.get(0);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return user;
    }
}
