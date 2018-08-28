package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.ClientAccessRepository;
import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.data.base.InvoiceRepository;
import com.spidermanteam.spiderpuppies.data.base.SubscriberRepository;
import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.models.reporting.PaymentReport;
import com.spidermanteam.spiderpuppies.models.reporting.PaymentReportStatus;
import com.spidermanteam.spiderpuppies.services.base.ClientAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ClientAccessServiceImpl implements ClientAccessService {

    private InvoiceRepository invoiceRepository;
    private SubscriberRepository subscriberRepository;

    @Autowired
    public ClientAccessServiceImpl(InvoiceRepository invoiceRepository, SubscriberRepository subscriberRepository) {
        this.invoiceRepository = invoiceRepository;
        this.subscriberRepository = subscriberRepository;
    }


    @Override
    public PaymentReport payInvoiceByIdAndClientId(int invoiceId, int clientId) {
        Invoice invoice = invoiceRepository.findByIdAndClientId(invoiceId, clientId);
        PaymentReport paymentReport = new PaymentReport(invoice.getSubscriber().getPhone(), invoiceId, PaymentReportStatus.FAILED_INVOICE_OR_CLIENT_NOT_EXIST);
        return payInvoice(invoice, paymentReport);
    }

    @Override
    public List<PaymentReport> payInvoicesByPhoneAndClientId(String phone, int clientId) {
        List<Invoice> invoiceList = invoiceRepository.findInvoicesByPhone(phone);
        List<PaymentReport> paymentReportList = new ArrayList<>();
        for (Invoice invoice : invoiceList
        ) {
            PaymentReport paymentReport = new PaymentReport(invoice.getSubscriber().getPhone(), invoice.getId(), PaymentReportStatus.FAILED_INVOICE_OR_CLIENT_NOT_EXIST);
            paymentReportList.add(payInvoice(invoice, paymentReport));
        }
        return paymentReportList;
    }

    @Override
    public List<PaymentReport> payInvoicesByIdListAndClientId(List<Integer> invoiceIdList, int clientId) {
        List<PaymentReport> paymentReportList = new ArrayList<>();
        for (int id : invoiceIdList
        ) {
            Invoice invoice = invoiceRepository.findByIdAndClientId(id, clientId);
            PaymentReport paymentReport = new PaymentReport(invoice.getSubscriber().getPhone(), invoice.getId(), PaymentReportStatus.FAILED_INVOICE_OR_CLIENT_NOT_EXIST);
            paymentReportList.add(payInvoice(invoice, paymentReport));
        }
        return paymentReportList;
    }


    @Override
    public List<PaymentReport> payInvoicesByPhoneListAndClientId(List<String> phonesList, int clientId) {
        List<PaymentReport> paymentReportList = new ArrayList<>();
        for (String phone : phonesList
        ) {
            List<Invoice> invoiceList = invoiceRepository.findInvoicesByPhoneAndClientId(phone, clientId);
            for (Invoice invoice : invoiceList
            ) {
                PaymentReport paymentReport = new PaymentReport(invoice.getSubscriber().getPhone(), invoice.getId(), PaymentReportStatus.FAILED_INVOICE_OR_CLIENT_NOT_EXIST);
                paymentReportList.add(payInvoice(invoice, paymentReport));
            }
        }
        return paymentReportList;
    }


    @Override
    public List<PaymentReport> payAllPendingInvoicesByClient(int clientId) {
        List<Invoice> invoiceList = invoiceRepository.findAllPendingInvoicesByClientId(clientId);

        List<PaymentReport> paymentReportList = new ArrayList<>();
        for (Invoice invoice : invoiceList
        ) {
            PaymentReport paymentReport = new PaymentReport(invoice.getSubscriber().getPhone(), invoice.getId(), PaymentReportStatus.FAILED_INVOICE_OR_CLIENT_NOT_EXIST);
            paymentReportList.add(payInvoice(invoice, paymentReport));
        }
        return paymentReportList;
    }

    @Override
    public List<Invoice> listAllPendingInvoicesByClientId(int clientId) {
        return invoiceRepository.findAllPendingInvoicesByClientId(clientId);
    }

    @Override
    public List<Invoice> listAllInvoicesByClientId(int clientId) {
        return invoiceRepository.findAllInvoicesByClientId(clientId)
                ;
    }

    @Override
    public Subscriber getSubscriberByPhoneAndClientId(String phone, int clientId) {
        return subscriberRepository.getSubscriberByPhoneAndClientId(phone, clientId);
    }

    @Override
    public BigDecimal getMaxPriceBySubscriberId(int subscriberId) {
        return subscriberRepository.getHighestPaidSumBySubscriber(subscriberId);
    }

    @Override
    public BigDecimal getAvgPriceBySubscriberId(int subscriberId) {
        return subscriberRepository.getAveragePaidSumBySubscriber(subscriberId);
    }

    @Override
    public List<Invoice> getLastTenPaidInvoiceBySubscriberId(int subscriberId) {
        return invoiceRepository.findLastTenPaymentsBySubscriberId(subscriberId);
    }

    @Override
    public List<Invoice> getLastTenPaidInvoiceByClient(int clientId) {
        return invoiceRepository.findLastTenPaymentsByClientId(clientId);
    }

    @Override
    public List<Subscriber> getTenBestSubscribersByTurnoverAndClientId(int clientId) {
        return subscriberRepository.getTenBestSubscribersByTurnoverAndClientId(clientId);
    }


    @Override
    public PaymentReport payInvoice(Invoice invoice, PaymentReport paymentReport) {
        if (!currencyCheck(invoice)) {
            invoiceCurrencyConverter(invoice);
            if (invoice.getCurrency().contains("Not_Supported")) {
                paymentReport.setStatus(PaymentReportStatus.FAILED_INVOICE_CURRENCY_NOT_SUPPORTED);
                return paymentReport;
            }

        }

        if (invoice.getStatus().equals("0")) {
            invoice.setStatus("1");
            invoice.setPaymentDate(LocalDate.now());
            BigDecimal price = invoice.getPrice();
            Subscriber subscriber = invoice.getSubscriber();
            BigDecimal currentTurnover = subscriber.getAllTimeTurnover();
            subscriber.setAllTimeTurnover(currentTurnover.add(price));
            paymentReport.setPhoneNum(subscriber.getPhone());
            paymentReport.setInvoiceId(invoice.getId());
            paymentReport.setStatus(PaymentReportStatus.SUCCESSFULLY_PAID);

            subscriberRepository.update(subscriber);
            invoiceRepository.update(invoice);
        }
        if (invoice.getStatus().equals("1")) {
            paymentReport.setPhoneNum(invoice.getSubscriber().getPhone());
            paymentReport.setInvoiceId(invoice.getId());
            paymentReport.setStatus(PaymentReportStatus.FAILED_ALREADY_PAID);
        }
        return paymentReport;
    }

    @Override
    public boolean currencyCheck(Invoice invoice) {
        return invoice.getCurrency().toLowerCase().equals("bgn");
    }

    @Override
    public void invoiceCurrencyConverter(Invoice invoice) {
        BigDecimal invoicePrice = invoice.getPrice();
        switch (invoice.getCurrency().toLowerCase()) {
            case "eur":
                invoicePrice = invoicePrice.multiply(BigDecimal.valueOf(1.95583));
                break;
            case "gbp":
                invoicePrice = invoicePrice.multiply(BigDecimal.valueOf(2.16833));
                break;
            case "usd":
                invoicePrice = invoicePrice.multiply(BigDecimal.valueOf(1.68781));
                break;
            case "chf":
                invoicePrice = invoicePrice.multiply(BigDecimal.valueOf(1.71594));
                break;
            default:
                String updateCurrency = invoice.getCurrency() + "Not_Supported";
                invoice.setCurrency(updateCurrency);
        }
        invoice.getTelecomServices().setPrice(invoicePrice);
        invoice.setCurrency("BGN");
    }

}
