package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.ClientAccessRepository;
import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.data.base.InvoiceRepository;
import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.services.base.ClientAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
public class ClientAccessServiceImpl implements ClientAccessService {

    private ClientAccessRepository clientAccessRepository;
    private InvoiceRepository invoiceRepository;
    private GenericRepository<Subscriber> subscriberRepository;

    @Autowired
    public ClientAccessServiceImpl(ClientAccessRepository clientAccessRepository, InvoiceRepository invoiceRepository, GenericRepository<Subscriber> subscriberRepository) {
        this.clientAccessRepository = clientAccessRepository;
        this.invoiceRepository = invoiceRepository;
        this.subscriberRepository = subscriberRepository;
    }


    @Override
    public void payInvoiceById(int invoiceId) {
        Invoice invoice = invoiceRepository.findById(invoiceId);
        if (invoice.getStatus().equals("0")) {
            invoice.setStatus("1");
            invoice.setPaymentDate(LocalDate.now());
            BigDecimal price = invoice.getPrice();
            Subscriber subscriber = invoice.getSubscriber();
            BigDecimal currentTurnover = subscriber.getAllTimeTurnover();
            subscriber.setAllTimeTurnover(currentTurnover.add(price));

            subscriberRepository.update(subscriber);
            invoiceRepository.update(invoice);
        }
    }

    @Override
    public void payInvoicesByPhone(String phone) {
        List<Invoice> invoiceList = invoiceRepository.findInvoicesByPhone(phone);
        for (Invoice invoice : invoiceList
        ) {
            if (invoice.getStatus().equals("0")) {
                invoice.setStatus("1");
                invoice.setPaymentDate(LocalDate.now());
                BigDecimal price = invoice.getPrice();
                Subscriber subscriber = invoice.getSubscriber();
                BigDecimal currentTurnover = subscriber.getAllTimeTurnover();
                subscriber.setAllTimeTurnover(currentTurnover.add(price));

                subscriberRepository.update(subscriber);
                invoiceRepository.update(invoice);

            }
        }

    }

    @Override
    public void payInvoicesByIdList(List<Integer> invoiceIdList) {
        for (int id : invoiceIdList
        ) {
            Invoice invoice = invoiceRepository.findById(id);

            if (invoice.getStatus().equals("0")) {
                invoice.setStatus("1");
                invoice.setPaymentDate(LocalDate.now());
                BigDecimal price = invoice.getPrice();
                Subscriber subscriber = invoice.getSubscriber();
                BigDecimal currentTurnover = subscriber.getAllTimeTurnover();
                subscriber.setAllTimeTurnover(currentTurnover.add(price));

                subscriberRepository.update(subscriber);
                invoiceRepository.update(invoice);
            }
        }
    }

    @Override
    public void payInvoicesByPhoneList(List<String> phonesList) {
        clientAccessRepository.payInvoicesByPhoneList(phonesList);

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
