package com.spidermanteam.spiderpuppies.services.base;

import com.spidermanteam.spiderpuppies.models.Invoice;


import java.util.List;

public interface InvoiceService {
    void addInvoice(Invoice invoice);

    Invoice findInvoiceById(int id);

    List listAllInvoices();

    void deleteInvoice(int id);

    void updateInvoice (Invoice invoice);


}