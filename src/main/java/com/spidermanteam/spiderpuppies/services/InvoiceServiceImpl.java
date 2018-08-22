package com.spidermanteam.spiderpuppies.services;

import com.spidermanteam.spiderpuppies.data.base.GenericRepository;
import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.services.base.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private GenericRepository<Invoice> invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(GenericRepository<Invoice> invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }


    @Override
    public void addInvoice(Invoice invoice) {
        invoiceRepository.create(invoice);

    }

    @Override
    public Invoice findInvoiceById(int id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public List listAllInvoices() {
        return invoiceRepository.listAll();
    }

    @Override
    public void deleteInvoice(int id) {
        invoiceRepository.listAll();

    }

    @Override
    public void updateInvoice(Invoice invoice) {
        invoiceRepository.update(invoice);

    }
}
