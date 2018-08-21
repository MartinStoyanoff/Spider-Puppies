package com.spidermanteam.spiderpuppies.data.base;

import com.spidermanteam.spiderpuppies.models.Invoice;

public interface InvoiceRepository extends GenericRepository<Invoice> {

    Invoice findInvoicesByPhone(String phone);
}
