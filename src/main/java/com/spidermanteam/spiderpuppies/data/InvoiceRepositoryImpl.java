package com.spidermanteam.spiderpuppies.data;

import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.data.base.InvoiceRepository;
import com.spidermanteam.spiderpuppies.models.Invoice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InvoiceRepositoryImpl implements GenericRepository<Invoice>, InvoiceRepository {


    private SessionFactory sessionFactory;

    @Autowired
    public InvoiceRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void create(Invoice invoice) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(invoice);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Invoice findById(int id) {
        Invoice invoice = new Invoice();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            invoice = session.get(Invoice.class, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return invoice;
    }

    @Override
    public List<Invoice> listAll() {
        List<Invoice> invoices = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            invoices = session.createQuery("from Invoice ").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return invoices;
    }

    @Override
    public void update(Invoice invoice) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(invoice);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Invoice invoice = session.get(Invoice.class, id);
            session.delete(invoice);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Invoice> findInvoicesByPhone(String phone) {
        List<Invoice> invoiceList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "from Invoice as i where i.subscriber.phone=:phoneNum";
            invoiceList = session.createQuery(query)
                    .setParameter("phoneNum", phone).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return invoiceList;
    }
}
