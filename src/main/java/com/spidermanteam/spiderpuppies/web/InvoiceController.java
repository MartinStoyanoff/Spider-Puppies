package com.spidermanteam.spiderpuppies.web;


import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.services.base.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InvoiceController {

    private InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }
    @PostMapping("/admin/addInvoice")
    void addInvoice (@RequestBody Invoice invoice){
        invoiceService.addInvoice(invoice);
    }

    @GetMapping("/admin/findInvoiceById/{id}")
    public Invoice findInvoiceById(@PathVariable("id") int id) {
        return invoiceService.findInvoiceById(id);
    }

    @GetMapping("/admin/listAllInvoices")
    List listAllInvoice(){
        return invoiceService.listAllInvoices();
    }

    @PutMapping("/admin/updateInvoice")
    void updateInvoice(@RequestBody Invoice invoice){
        invoiceService.updateInvoice(invoice);
    }

    @DeleteMapping("/admin/deleteInvoice/{id}")
    void deleteInvoice(@PathVariable("id") int id){
        invoiceService.deleteInvoice(id);
    }

}
