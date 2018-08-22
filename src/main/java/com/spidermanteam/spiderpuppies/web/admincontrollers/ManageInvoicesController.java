package com.spidermanteam.spiderpuppies.web.admincontrollers;


import com.spidermanteam.spiderpuppies.models.reporting.InvoiceReport;
import com.spidermanteam.spiderpuppies.objectmapping.*;
import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.models.TelecomService;
import com.spidermanteam.spiderpuppies.services.base.InvoiceService;
import com.spidermanteam.spiderpuppies.services.base.SubscribersService;
import com.spidermanteam.spiderpuppies.services.base.TelecomServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin/manage/invoices")
public class ManageInvoicesController {

    private InvoiceService invoiceService;
    private TelecomServiceService telecomService;
    private SubscribersService subscribersService;

    @Autowired
    public ManageInvoicesController(InvoiceService invoiceService, TelecomServiceService telecomService, SubscribersService subscribersService) {
        this.invoiceService = invoiceService;
        this.telecomService = telecomService;
        this.subscribersService = subscribersService;
    }

    //TODO: implement custom serializer or additional models

    @PostMapping("/add")
    void addInvoice(@RequestBody HashMap<String, String> input) {
        Subscriber subscriber = subscribersService.findSubscriberById(Integer.parseInt(input.get("subscriber")));
        List<TelecomService> list = subscriber.getTelecomServices();
        for (TelecomService ts : list
        ) {
            String currency = input.get("currency");
            Invoice invoiceAdd = new Invoice(subscriber, ts, currency);
            invoiceService.addInvoice(invoiceAdd);
        }

    }

    @GetMapping("/findById/{id}")
    public InvoiceReport findInvoiceById(@PathVariable int id) {
        Invoice invoice = invoiceService.findInvoiceById(id);
        return MappingHelper.mapInvoiceToInvoiceReport(invoice);
    }

    @GetMapping("/listAll")
    List listAllInvoice() {
        List<Invoice> invoiceList = invoiceService.listAllInvoices();
        List<InvoiceReport> invoiceReportList = new ArrayList<>();
        for (Invoice invoice:invoiceList
             ) {

            InvoiceReport invoiceReport = MappingHelper.mapInvoiceToInvoiceReport(invoice);
            invoiceReportList.add(invoiceReport);

        }
        return invoiceReportList;
    }

    @PutMapping("/update")
    void updateInvoice(@RequestBody Invoice invoice) {
        invoiceService.updateInvoice(invoice);
    }

    @DeleteMapping("/delete/{id}")
    void deleteInvoice(@PathVariable int id) {
        invoiceService.deleteInvoice(id);
    }
}
