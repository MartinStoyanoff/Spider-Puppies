package com.spidermanteam.spiderpuppies.web.admincontrollers;


import com.spidermanteam.spiderpuppies.models.reporting.InvoiceRequestModel;
import com.spidermanteam.spiderpuppies.models.reporting.InvoiceView;
import com.spidermanteam.spiderpuppies.models.Invoice;
import com.spidermanteam.spiderpuppies.models.Subscriber;
import com.spidermanteam.spiderpuppies.services.base.InvoiceService;
import com.spidermanteam.spiderpuppies.services.base.SubscribersService;
import com.spidermanteam.spiderpuppies.services.base.TelecomServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
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
        String subscriberId = input.get("id");
        String currency = input.get("currency");
        invoiceService.addInvoice(subscriberId,currency);

    }

    @GetMapping("/findById/{id}")
    public InvoiceView findInvoiceById(@PathVariable int id) {
        Invoice invoice = invoiceService.findInvoiceById(id);
        return new InvoiceView(invoice);
    }

    @GetMapping("/listAll")
    List listAllInvoice() {
        List<Invoice> invoiceList = invoiceService.listAllInvoices();
        List<InvoiceView> invoiceViewList = new ArrayList<>();
        for (Invoice invoice : invoiceList
        ) {

            InvoiceView invoiceView = new InvoiceView(invoice);
            invoiceViewList.add(invoiceView);

        }
        return invoiceViewList;
    }

    @PutMapping("/update")
    void updateInvoice(@RequestBody Invoice invoice) {
        invoiceService.updateInvoice(invoice);
    }

    @DeleteMapping("/delete/{id}")
    void deleteInvoice(@PathVariable int id) {
        invoiceService.deleteInvoice(id);
    }

    /*
    * /admin/bulkpayment - Generate Button
    * */
    @PostMapping("/bulkgenerate")
    void generateBulkPayment(@RequestBody List<InvoiceRequestModel> subscribersIdList) {

        HashMap<String,String> hashMap = new HashMap<>();



        for (InvoiceRequestModel inv: subscribersIdList
             ) {
            hashMap.put(inv.getSubscriberId(),inv.getCurrency());

        }
        invoiceService.generateBulkPayment(hashMap);
    }

    @GetMapping("/lastTen/{id}")
    public List<InvoiceView> findLastTenPaymentsBySubscriber(@PathVariable int id) {
        Subscriber subscriber = subscribersService.findSubscriberById(id);
        List<Invoice> invoices = invoiceService.findLastTenPaymentsBySubscriberId(subscriber.getId());
        List<InvoiceView> invoiceViews = new ArrayList();
        for (Invoice inv : invoices) {
            InvoiceView invoiceView = new InvoiceView(inv);
            invoiceViews.add(invoiceView);
        }
        return invoiceViews;
    }

}
