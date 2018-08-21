package com.spidermanteam.spiderpuppies.data.base;

import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;

import java.math.BigDecimal;
import java.util.List;

public interface ClientAccessRepository {

    void payInvoiceById(int invoiceId);

    void payInvoicesByPhone(String phone);

    void payInvoicesByIdList(List<Integer> invoiceIdList);

    void payInvoicesByPhoneList(List<String> phonesList);

    void payAllUnpaidInvoiceByClient(int clientId);

    List<Invoice> listAllUnpaidInvoiceById(int clientId);

    List<Invoice> listAllPaidInvoicesById(int clientId);

    Subscriber getSubscriberByPhone(String phone);

    BigDecimal getMaxPriceBySubscriberPhone(String phone);

    BigDecimal getAvgPriceBySubscriberPhone(String phone);

    List<Invoice> getLastTenPaidInvoiceBySubscriberPhone(String phone);

    BigDecimal getMaxPriceFromAllSubscribers(int clientId);

    BigDecimal getAvgPriceFromSubscribers(int clientId);

    List<Invoice> getLastTenPaidInvoiceByClient(int clientId);


}
