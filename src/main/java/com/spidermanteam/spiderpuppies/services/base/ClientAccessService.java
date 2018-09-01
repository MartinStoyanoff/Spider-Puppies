package com.spidermanteam.spiderpuppies.services.base;

import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.models.reporting.PaymentReport;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface ClientAccessService {

    PaymentReport payInvoiceByIdAndClientId(int invoiceId, int clientId);

    List<PaymentReport> payInvoicesByPhoneAndClientId(String phone, int clientId);

    List<PaymentReport> payInvoicesByIdListAndClientId(List<Integer> invoiceIdList, int clientId);

    List<PaymentReport> payInvoicesByPhoneListAndClientId(List<String> phonesList, int clientId);

    List<PaymentReport> payAllPendingInvoicesByClient(int clientId);

    List<Invoice> listAllPendingInvoicesByClientId(int clientId);

    List<Invoice> listAllInvoicesByClientId(int clientId);

    Subscriber getSubscriberByPhoneAndClientId(String phone, int clientId);

    BigDecimal getMaxPriceBySubscriberId(int subscriberId);

    BigDecimal getAvgPriceBySubscriberId(int subscriberId);

    List<Invoice> getLastTenPaidInvoiceBySubscriberId(int subscriberId);

    List<Invoice> getLastTenPaidInvoiceByClient(int clientId);

    List<Subscriber> getTenBestSubscribersByTurnoverAndClientId(int clientId);

    PaymentReport payInvoice(Invoice invoice, PaymentReport paymentReport);

    boolean currencyCheck(Invoice invoice);

    void invoiceCurrencyConverter(Invoice invoice);

    BigDecimal getAveragePaidSumBySubscriber(int id);



}
