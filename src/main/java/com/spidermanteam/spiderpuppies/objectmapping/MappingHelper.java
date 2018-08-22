package com.spidermanteam.spiderpuppies.objectmapping;

import com.spidermanteam.spiderpuppies.models.Client;
import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.models.TelecomService;
import com.spidermanteam.spiderpuppies.models.reporting.InvoiceReport;
import com.spidermanteam.spiderpuppies.models.reporting.SubscriberReport;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MappingHelper {

    public static InvoiceReport mapInvoiceToInvoiceReport(Invoice invoice) {
        int id = invoice.getId();
        String subscriberName = invoice.getSubscriber().getFirstName() + " " + invoice.getSubscriber().getLastName();
        String subscriberPhone = invoice.getSubscriber().getPhone();
        String telecomServiceType = invoice.getTelecomServices().getType();
        String telecomServiceSubscriptionPlan = invoice.getTelecomServices().getSubscriptionPlan();
        String status = invoice.getStatus();
        BigDecimal price = invoice.getPrice();
        String currency = invoice.getCurrency();
        LocalDate startDate = invoice.getStartDate();
        LocalDate endDate = invoice.getEndDate();
        LocalDate paymentDate = invoice.getPaymentDate();

        return new InvoiceReport(id, subscriberName, subscriberPhone, telecomServiceType, telecomServiceSubscriptionPlan,
                status, price, currency, startDate, endDate, paymentDate);
    }

    public static SubscriberReport mapSubscriberToSubscriberReport(Subscriber subscriber) {

        int id = subscriber.getId();
        String phone = subscriber.getPhone();
        String firstName = subscriber.getFirstName();
        String lastName = subscriber.getLastName();
        String personalIdentificationNumber = subscriber.getPersonalIdentificationNumber();
        String address = subscriber.getAddress();
        List<Invoice> invoices = subscriber.getInvoices();

        List<InvoiceReport> invoiceReportList = new ArrayList<>();
        for (Invoice inv:invoices
             ) {
            InvoiceReport invReport = MappingHelper.mapInvoiceToInvoiceReport(inv);
            invoiceReportList.add(invReport);
        }

        LocalDate firstServiceActivationDate = subscriber.getFirstServiceActivationDate();
        LocalDate billingDate = subscriber.getBillingDate();
        Client client = subscriber.getClient();
        BigDecimal allTimeTurnover = subscriber.getAllTimeTurnover();

        return new SubscriberReport(id, phone, firstName, lastName, personalIdentificationNumber, address, invoiceReportList , firstServiceActivationDate, billingDate, client, allTimeTurnover);
    }
}