package com.spidermanteam.spiderpuppies.objectmapping;

import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.reporting.InvoiceReport;

import java.math.BigDecimal;
import java.time.LocalDate;

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
}