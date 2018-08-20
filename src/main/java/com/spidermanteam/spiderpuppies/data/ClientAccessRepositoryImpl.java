package com.spidermanteam.spiderpuppies.data;

import com.spidermanteam.spiderpuppies.data.base.ClientAccessRepository;
import com.spidermanteam.spiderpuppies.models.Invoice;
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
public class ClientAccessRepositoryImpl implements ClientAccessRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public ClientAccessRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void payInvoiceById(long invoiceId) {
        Invoice invoice;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            invoice = session.get(Invoice.class, invoiceId);
            invoice.setStatus("1");
            invoice.setPaymentDate(LocalDate.now());
            session.save(invoice);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void payInvoiceByPhone(String phoneNum) {
        List<Invoice> invoiceList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String query = "from Invoice as i where i.subscriber.phone=:phoneNum";
            invoiceList = session.createQuery(query)
                    .setParameter("phoneNum", phoneNum).list();
            for (Invoice inv : invoiceList
            ) {
                if (inv.getSubscriber().getPhone().equals(phoneNum)) {
                    if (inv.getStatus().equals("0")) {
                        inv.setStatus("1");
                        inv.setPaymentDate(LocalDate.now());
                        session.save(inv);
                    }
                }
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    @Override
    public void payInvoicesByIdList(List<Integer> invoiceIdList) {
        Invoice invoice;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            for (int id : invoiceIdList
            ) {
                invoice = session.get(Invoice.class, id);
                invoice.setStatus("1");
                invoice.setPaymentDate(LocalDate.now());
                session.save(invoice);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void payInvoicesByPhoneList(List<String> phonesList) {
        List<Invoice> invoiceList = new ArrayList<>();
        Invoice invoice;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            for (String phone : phonesList
            ) {
                String query = String.format("from Invoice where phone like %s", phone);
                invoiceList = session.createQuery(query).list();
                invoice = invoiceList.get(0);
                invoice.setStatus("1");
                invoice.setPaymentDate(LocalDate.now());
                session.save(invoice);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void payAllUnpaidInvoiceByClient(int clientId) {

    }

    @Override
    public List<Invoice> listAllUnpaidInvoiceById(int clientId) {
        return null;
    }

    @Override
    public List<Invoice> listAllPaidInvoicesById(int clientId) {
        return null;
    }

    @Override
    public Subscriber getSubscriberByPhone(String phone) {
        return null;
    }

    @Override
    public BigDecimal getMaxPriceBySubscriberPhone(String phone) {
        return null;
    }

    @Override
    public BigDecimal getAvgPriceBySubscriberPhone(String phone) {
        return null;
    }

    @Override
    public List<Invoice> getLastTenPaidInvoiceBySubscriberPhone(String phone) {
        return null;
    }

    @Override
    public BigDecimal getMaxPriceFromAllSubscribers(int clientId) {
        return null;
    }

    @Override
    public BigDecimal getAvgPriceFromSubscribers(int clientId) {
        return null;
    }

    @Override
    public List<Invoice> getLastTenPaidInvoiceByClient(int clientId) {
        return null;
    }
}
