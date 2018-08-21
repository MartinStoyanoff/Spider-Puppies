package com.spidermanteam.spiderpuppies.data.base;

import com.spidermanteam.spiderpuppies.models.Invoice;

import java.util.List;

public interface InvoiceRepository extends GenericRepository<Invoice> {

   List<Invoice> findInvoicesByPhone(String phone);

}
