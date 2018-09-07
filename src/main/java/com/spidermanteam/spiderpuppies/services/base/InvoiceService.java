package com.spidermanteam.spiderpuppies.services.base;

import com.spidermanteam.spiderpuppies.models.Invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface InvoiceService {
  void addInvoice(String subscriberId, String currency);

  Invoice findInvoiceById(int id);

  List listAllInvoices();

  void deleteInvoice(int id);

  void updateInvoice(Invoice invoice);

  List<Invoice> findAllPendingInvoicesByClientId(int id);

  List<Invoice> findAllInvoicesByClientId(int id);

  void generateBulkPayment(HashMap<String, String> invoiceInfoList);

  List<Invoice> findLastTenPaymentsBySubscriberId(int id);

  List<Invoice> findLastTenPayments();

  BigDecimal getExchangeRateToBGN(String currency);
}
