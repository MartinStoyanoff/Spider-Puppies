package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.ClientAccessRepository;
import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.data.base.InvoiceRepository;
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
    private GenericRepository<Subscriber> subscriberRepository;

    @Autowired
    public ClientAccessServiceImpl(InvoiceRepository invoiceRepository, GenericRepository<Subscriber> subscriberRepository) {
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

    private PaymentReport payInvoice(Invoice invoice, PaymentReport paymentReport) {

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
}
