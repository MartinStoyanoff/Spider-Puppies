package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.InvoiceRepository;
import com.spidermanteam.spiderpuppies.data.base.SubscriberRepository;
import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.models.TelecomService;
import com.spidermanteam.spiderpuppies.services.base.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void addInvoice(String subscriberId, String currency) {
        Subscriber subscriber = subscriberRepository.findById(Integer.parseInt(subscriberId));
        BigDecimal exchangeRate = BigDecimal.valueOf(1);
        if (!currency.toLowerCase().equals("bgn")) {
            exchangeRate = getExchangeRateToBGN(currency);
        }

        List<TelecomService> telecomServiceList = subscriber.getTelecomServices();
        for (TelecomService ts : telecomServiceList
        ) {
            BigDecimal price = ts.getPrice().divide(exchangeRate, 5, 5);
            Invoice invoice = new Invoice(subscriber, ts, price, currency);
            invoiceRepository.create(invoice);
        }

        LocalDate billingDate = subscriber.getBillingDate();
        LocalDate newBillingDate = billingDate.plusMonths(1);
        subscriber.setBillingDate(newBillingDate);
        subscriberRepository.update(subscriber);

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
    public void generateBulkPayment(HashMap<String, String> invoiceInfoList) {
        for (Map.Entry<String,String> entry:invoiceInfoList.entrySet()) {
            String subscriberId = entry.getKey();
            String currency = entry.getValue();
            addInvoice(subscriberId, currency);
        }
    }

    @Override
    public List<Invoice> findLastTenPaymentsBySubscriberId(int id) {
        return invoiceRepository.findLastTenPaymentsBySubscriberId(id);
    }

    @Override
    public List<Invoice> findLastTenPayments() {
        return invoiceRepository.findLastTenPayments();
    }

    @Override
    public BigDecimal getExchangeRateToBGN(String currency) {
        BigDecimal exchangeRate;
        switch (currency.toLowerCase()) {
            case "eur":
                exchangeRate = (BigDecimal.valueOf(1.95583));
                break;
            case "gbp":
                exchangeRate = (BigDecimal.valueOf(2.16833));
                break;
            case "usd":
                exchangeRate = (BigDecimal.valueOf(1.68781));
                break;
            case "chf":
                exchangeRate = (BigDecimal.valueOf(1.71594));
                break;
            default:
                exchangeRate = BigDecimal.valueOf(1);
        }
        return exchangeRate;
    }
}
