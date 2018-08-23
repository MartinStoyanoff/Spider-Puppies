package com.spidermanteam.spiderpuppies.services.base;

import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface ClientAccessService {
    void payInvoiceById(int invoiceId);

    void payInvoicesByPhone(String phone);

    void payInvoicesByIdList(List<Integer> invoiceIdList);

    void payInvoicesByPhoneList(List<String> phonesList);

    void payAllPendingInvoicesByClient(int clientId);

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
