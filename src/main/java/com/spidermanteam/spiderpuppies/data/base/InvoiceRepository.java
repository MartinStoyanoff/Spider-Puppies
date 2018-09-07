package com.spidermanteam.spiderpuppies.data.base;

import com.spidermanteam.spiderpuppies.models.Invoice;

import java.util.List;

public interface InvoiceRepository extends GenericRepository<Invoice> {

  Invoice findByIdAndClientId(int id, int clientId);

  List<Invoice> findInvoicesByPhoneAndClientId(String phone, int clientId);

  List<Invoice> findInvoicesByPhone(String phone);

  List<Invoice> findAllPendingInvoicesByClientId(int clientId);

  List<Invoice> findAllInvoicesByClientId(int clientId);

  List<Invoice> findLastTenPaymentsBySubscriberId(int subscriberId);

  List<Invoice> findLastTenPaymentsByClientId(int clientId);

  List<Invoice> findLastTenPayments();

  List<Invoice> findDueInvoicesByPhone(String phone);

}
