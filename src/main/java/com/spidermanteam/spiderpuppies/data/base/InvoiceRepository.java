package com.spidermanteam.spiderpuppies.data.base;

import com.spidermanteam.spiderpuppies.models.Invoice;

import java.util.List;

public interface InvoiceRepository extends GenericRepository<Invoice> {

    List<Invoice> findInvoicesByPhone(String phone);

    List<Invoice> findAllPendingInvoicesByClientId(int id);

    List<Invoice> findAllInvoicesByClientId(int id);

    List<Invoice> findLastTenPaymentsBySubscriberId(int id);

}
