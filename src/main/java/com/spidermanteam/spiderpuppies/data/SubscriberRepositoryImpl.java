package com.spidermanteam.spiderpuppies.data;

import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.data.base.SubscriberRepository;
import com.spidermanteam.spiderpuppies.models.Authorities;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SubscriberRepositoryImpl implements SubscriberRepository {
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

    @Override
    public List<Subscriber> findAllForDefinedPeriod(LocalDate start, LocalDate end) {
        List subscribers = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            subscribers = session.createQuery("from Subscriber as s where s.billingDate>=:startDate and s.billingDate<=:endDate")
                    .setParameter("startDate", start)
                    .setParameter("endDate", end).list();
            session.getTransaction().commit();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return subscribers;
    }

    @Override
    public BigDecimal getHighestPaidSumBySubscriber(int id) {
        BigDecimal amount = null;
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<BigDecimal> max = session.createQuery("select max(i.price) from Subscriber as s join Invoice as i on s.id=i.subscriber.id where i.status=:status")
                    .setParameter("status", "1")
                    .list();
            amount = max.get(0);
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return amount;
    }

    @Override
    public BigDecimal getAveragePaidSumBySubscriber(int id) {
        BigDecimal amount = null;
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            List<Double> max = session.createQuery("select avg(i.price) from Subscriber as s join Invoice as i on s.id=i.subscriber.id where i.status=:status")
                    .setParameter("status", "1")
                    .list();
            amount =  BigDecimal.valueOf(max.get(0));
            session.getTransaction().commit();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return amount;
    }

    @Override
    public Subscriber getSubscriberByPhoneAndClientId(String phone, int clientId) {
        Subscriber subscriber = new Subscriber();
        List subscriberList;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            subscriberList = session.createQuery("from Subscriber as s where s.phone=:phone and s.client.id=:id")
            .setParameter("phone",phone)
                    .setParameter("id",clientId).list();
            subscriber=(Subscriber) subscriberList.get(0);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return subscriber;
    }

    @Override
    public List<Subscriber> getTenBestSubscribersByTurnoverAndClientId(int clientId) {
        List subscriberList = new ArrayList();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            subscriberList = session.createQuery("from Subscriber as s " +
                    "where s.client.id=:clientId " +
                    "order by s.allTimeTurnover desc")
                    .setParameter("clientId", "1")
                    .setMaxResults(10)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return subscriberList;
    }
    }


