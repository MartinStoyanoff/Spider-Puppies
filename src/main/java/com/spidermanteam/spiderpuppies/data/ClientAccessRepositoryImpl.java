//package com.spidermanteam.spiderpuppies.data;
//
//import com.spidermanteam.spiderpuppies.data.base.ClientAccessRepository;
//import com.spidermanteam.spiderpuppies.models.Invoice;
//import com.spidermanteam.spiderpuppies.models.Subscriber;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class ClientAccessRepositoryImpl implements ClientAccessRepository {
//
//    private SessionFactory sessionFactory;
//
//    @Autowired
//    public ClientAccessRepositoryImpl(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//
//    @Override
//    public void payInvoicesByIdList(List<Integer> invoiceIdList) {
//        Invoice invoice;
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            for (int id : invoiceIdList
//            ) {
//                invoice = session.get(Invoice.class, id);
//                if (invoice.getStatus().equals("0")) {
//                    invoice.setStatus("1");
//                    invoice.setPaymentDate(LocalDate.now());
//                    session.save(invoice);
//                }
//            }
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Override
//    public void payInvoicesByPhoneList(List<String> phonesList) {
//        List<Invoice> invoiceList = new ArrayList<>();
//        Invoice invoice;
//        try (Session session = sessionFactory.openSession()) {
//            session.beginTransaction();
//            for (String phone : phonesList
//            ) {
//                String query = "from Invoice as i where i.subscriber.phone=:phoneNum and i.status=:status";
//                invoiceList = session.createQuery(query)
//                        .setParameter("phoneNum", phone)
//                        .setParameter("status", "0").list();
//                for (Invoice inv : invoiceList
//                )
//                    if (inv.getStatus().equals("0")) {
//                        inv.setStatus("1");
//                        inv.setPaymentDate(LocalDate.now());
//                        session.save(inv);
//                    }
//            }
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//}