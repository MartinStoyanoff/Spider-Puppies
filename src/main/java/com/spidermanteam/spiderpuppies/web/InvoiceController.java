package com.spidermanteam.spiderpuppies.web;


import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.models.TelecomService;
import com.spidermanteam.spiderpuppies.services.base.InvoiceService;
import com.spidermanteam.spiderpuppies.services.base.SubscribersService;
import com.spidermanteam.spiderpuppies.services.base.TelecomServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class InvoiceController {

    private InvoiceService invoiceService;
    private TelecomServiceService telecomService;
    private SubscribersService subscribersService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService, TelecomServiceService telecomService, SubscribersService subscribersService) {
        this.invoiceService = invoiceService;
        this.telecomService = telecomService;
        this.subscribersService = subscribersService;
    }


    @PostMapping("/admin/addInvoice")
    void addInvoice(@RequestBody HashMap<String, String> input) {
        Subscriber subscriber = subscribersService.findSubscriberById(Integer.parseInt(input.get("subscriber")));
      List<TelecomService> list = subscriber.getTelecomServices();
        for (TelecomService ts: list
             ) {
            String currency = input.get("currency");
            Invoice invoiceAdd = new Invoice(subscriber, ts, currency);
            invoiceService.addInvoice(invoiceAdd);

        }

    }

    @GetMapping("/admin/findInvoiceById/{id}")
    public Invoice findInvoiceById(@PathVariable("id") int id) {
        return invoiceService.findInvoiceById(id);
    }

    @GetMapping("/admin/listAllInvoices")
    List listAllInvoice() {
        return invoiceService.listAllInvoices();
    }

    @PutMapping("/admin/updateInvoice")
    void updateInvoice(@RequestBody Invoice invoice) {
        invoiceService.updateInvoice(invoice);
    }

    @DeleteMapping("/admin/deleteInvoice/{id}")
    void deleteInvoice(@PathVariable("id") int id) {
        invoiceService.deleteInvoice(id);
    }


}
