package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.data.base.InvoiceRepository;
import com.spidermanteam.spiderpuppies.data.base.SubscriberRepository;
import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.models.TelecomService;
import com.spidermanteam.spiderpuppies.models.reporting.InvoiceReport;
import com.spidermanteam.spiderpuppies.objectmapping.MappingHelper;
import com.spidermanteam.spiderpuppies.services.base.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;
    private SubscriberRepository subscriberRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository, SubscriberRepository subscriberRepository) {
        this.invoiceRepository = invoiceRepository;
        this.subscriberRepository = subscriberRepository;
    }


    @Override
    public void addInvoice(Invoice invoice) {
        invoiceRepository.create(invoice);

    }

    @Override
    public Invoice findInvoiceById(int id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public List listAllInvoices() {
        return invoiceRepository.listAll();
    }

    @Override
    public void deleteInvoice(int id) {
        invoiceRepository.listAll();

    }

    @Override
    public void updateInvoice(Invoice invoice) {
        invoiceRepository.update(invoice);

    }

    @Override
    public List<Invoice> findAllPendingInvoicesByClientId(int id) {
        return invoiceRepository.findAllPendingInvoicesByClientId(id);
    }

    @Override
    public List<Invoice> findAllInvoicesByClientId(int id) {
        return invoiceRepository.findAllInvoicesByClientId(id);
    }

    @Override
    public void generateBulkPayment(List<Integer> subscribersIdList) {
        for (int id : subscribersIdList) {
            Subscriber subscriber = subscriberRepository.findById(id);
            List<TelecomService> telecomServices = subscriber.getTelecomServices();
            String currency = "BGN";
            for (TelecomService ts : telecomServices) {
                Invoice invoice = new Invoice(subscriber, ts, currency);
                invoiceRepository.create(invoice);
            }
            LocalDate billingDate = subscriber.getBillingDate();
            LocalDate newBillingDate = billingDate.plusMonths(1);
            subscriber.setBillingDate(newBillingDate);
            subscriberRepository.update(subscriber);
        }
    }

    @Override
    public List<Invoice> findLastTenPaymentsBySubscriberId(int id) {
        return invoiceRepository.findLastTenPaymentsBySubscriberId(id);
    }
}
