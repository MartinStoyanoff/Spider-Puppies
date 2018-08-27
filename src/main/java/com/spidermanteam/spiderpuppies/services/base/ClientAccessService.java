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

    Subscriber getSubscriberByPhone(String phone);

    BigDecimal getMaxPriceBySubscriberPhone(String phone);

    BigDecimal getAvgPriceBySubscriberPhone(String phone);

    List<Invoice> getLastTenPaidInvoiceBySubscriberPhone(String phone);

    BigDecimal getMaxPriceFromAllSubscribers(int clientId);

    BigDecimal getAvgPriceFromSubscribers(int clientId);

    List<Invoice> getLastTenPaidInvoiceByClient(int clientId);


}
